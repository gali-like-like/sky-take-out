package com.sky.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.command.MapInitializer;
import com.sky.constant.MessageConstant;
import com.sky.controller.user.OrdersController;
import com.sky.convert.OrderConvert;
import com.sky.dto.*;
import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import com.sky.exception.OrderBusinessException;
import com.sky.mapper.OrderDetailMapper;
import com.sky.mapper.OrdersMapper;
import com.sky.result.PageResult;
import com.sky.server.WebSocketService;
import com.sky.service.OrdersService;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.OrderedIterator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 订单表(Orders)表服务实现类
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:03
 */
@Service("ordersService")
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    // 需要注入的mapper

    private final OrderDetailMapper orderDetailMapper;
    private final OrdersMapper ordersMapper;
    private final MapInitializer mapInitializer;
    private final OrdersController ordersController;
    private final WebSocketService webSocketService;
    //订单搜索条件
    @Override
    public PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO) {
        PageHelper.startPage(ordersPageQueryDTO.getPage(), ordersPageQueryDTO.getPageSize());
        List<Orders> orders = ordersMapper.pageQuery(ordersPageQueryDTO);
        List<OrderVO> collect = orders.stream()
                .map(order -> {
                    OrderVO orderVO = new OrderVO();
                    OrderConvert.INSTANCE.orderToOrderVo(order, orderVO);
                    return orderVO;
                })
                .collect(Collectors.toList());
        PageInfo<OrderVO> pageInfo = new PageInfo<>(collect);
        return new PageResult(pageInfo.getTotal(), collect);
    }

    //订单详情
    @Override
    public OrderVO details(Long id) {
        Orders orders = ordersMapper.getById(id);
        List<OrderDetail> orderDetailList = orderDetailMapper.getOrderDetailByOrderId(orders.getId());
        OrderVO orderVO = new OrderVO();
        OrderConvert.INSTANCE.orderToOrderVo(orders, orderVO);
        orderVO.setOrderDetailList(orderDetailList);
        return orderVO;
    }

    //统计
    @Override
    public OrderStatisticsVO statistics() {
        // 根据状态，分别查询出待接单、待派送、派送中的订单数量
        Integer toBeConfirmed = ordersMapper.countStatus(Orders.TO_BE_CONFIRMED);
        Integer confirmed = ordersMapper.countStatus(Orders.CONFIRMED);
        Integer deliveryInProgress = ordersMapper.countStatus(Orders.DELIVERY_IN_PROGRESS);

        // 将查询出的数据封装到orderStatisticsVO中响应
        OrderStatisticsVO orderStatisticsVO = new OrderStatisticsVO();
        orderStatisticsVO.setToBeConfirmed(toBeConfirmed);
        orderStatisticsVO.setConfirmed(confirmed);
        orderStatisticsVO.setDeliveryInProgress(deliveryInProgress);
        return orderStatisticsVO;
    }

    //完成订单
    @Override
    public Boolean completeOrder(Long orderId) {
        Orders orders = ordersMapper.getById(orderId);
        if (Objects.isNull(orders)) {
            throw new OrderBusinessException(MessageConstant.ORDER_NOT_FOUND);
        }
        ordersMapper.completeOrder(orderId);
        return true;
    }

    //拒绝接单
    @Override
    public Boolean rejection(OrdersRejectionDTO ordersRejectionDTO) {
        Long id = ordersRejectionDTO.getId();
        Orders order = ordersMapper.getById(id);
        if (Objects.isNull(order))
            throw new OrderBusinessException(MessageConstant.ORDER_NOT_FOUND);
        ordersMapper.reject(ordersRejectionDTO);
        return true;
    }

    //取消订单
    @Override
    public Boolean cancel(OrdersCancelDTO ordersCancelDTO) {
        Integer status = ordersMapper.getStatusById(ordersCancelDTO.getId());
        if (Objects.isNull(status)) {
            throw new OrderBusinessException(MessageConstant.ORDER_NOT_FOUND);
        }
        ordersMapper.cancel(ordersCancelDTO);
        return true;
    }

    //接单
    public Boolean confirm(Long orderId) {
        Orders orders = ordersMapper.getById(orderId);
        if (Objects.isNull(orders)) {
            throw new OrderBusinessException(MessageConstant.ORDER_NOT_FOUND);
        }
        ordersMapper.confirm(orderId);
        return true;
    }

    //再来一单
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    @Override
    public void addOrder(Long id) {
        Orders orders = ordersMapper.getById(id);
        List<OrderDetail> orderDetails = orderDetailMapper.getOrderDetailByOrderId(id);
        String ordersNumber = UUID.randomUUID().toString();
        orders.setId(null);
        orders.setNumber(ordersNumber);
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setNumber(ordersNumber);
        ordersDTO.setAmount(orders.getAmount());
        ordersDTO.setAddress(orders.getAddress());
        ordersDTO.setConsignee(orders.getConsignee());
        ordersDTO.setAddressBookId(orders.getAddressBookId());
        ordersDTO.setPhone(orders.getPhone());
        ordersDTO.setRemark(orders.getRemark());
        ordersDTO.setUserId(orders.getUserId());
        ordersDTO.setUserName(orders.getUserName());
        ordersDTO.setStatus(1);
        Long newOrderId =  ordersMapper.addOrder(ordersDTO);
        for (OrderDetail orderDetail:orderDetails) {
            orderDetail.setOrderId(newOrderId);
            orderDetailMapper.addOrderDetail(orderDetail);
        }
    }


    //查询用户最近完成的一单信息
    @Override
    public Orders getLastCompleteOrderById(Long userId) {
        return ordersMapper.getLastCompleteOrderById(userId);
    }

    //查询历史订单
    @Override
    public PageInfo<Orders> getOrderByUserIdAndStatus(OrdersHistoryPageQueryDTO ordersHistoryPageQueryDTO) {
        List<Orders> orders = ordersMapper.getOrderByUserIdAndStatus(ordersHistoryPageQueryDTO);
        PageHelper.startPage(ordersHistoryPageQueryDTO.getPage(),ordersHistoryPageQueryDTO.getPageSize());
        PageInfo<Orders> pageOrders = new PageInfo<>(orders);
        return pageOrders;
    }

    //下单
    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public HashMap<String, String> placeOrder(OrdersDTO ordersDTO) {
        Long userId = ordersDTO.getUserId();
        if(Objects.isNull(userId)) {
            HashMap<String,String> result = new HashMap<>();
            result.put("msg",MessageConstant.USER_NOT_FOUND);
            result.put("data",null);
            return result;
        }
        String orderId = UUID.randomUUID().toString();//订单编号
        ordersDTO.setNumber(orderId);
        List<OrderDetail> orderDetails = ordersDTO.getOrderDetails();
        ordersMapper.addOrder(ordersDTO);
        for (OrderDetail orderDetail:orderDetails) {
            //添加订单编号
            orderDetail.setName(orderId);
            orderDetailMapper.addOrderDetail(orderDetail);
        }
        HashMap<String,String> result = new HashMap<>();
        result.put("msg",MessageConstant.ONE_MORE_ORDER_SUCCESS);
        result.put("data",orderId);
        return result;
    }

    //订单支付
    //没做真的支付接口
    @Override
    public void orderPay(OrdersPaymentDTO ordersPaymentDTO) {
        ordersMapper.orderPay(ordersPaymentDTO);
    }
    //催单
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    @Override
    public void reminders(Long id) {
        Long userId = ordersMapper.getUserIdById(id);
        String orderNumber = ordersMapper.getNumberById(id);
        webSocketService.sendToAllClient(userId,true,orderNumber+"催单了");
    }
    //派送订单
    @Override
    public Boolean delivery(Long id) {
        Integer status = ordersMapper.getStatusById(id);
        if (Objects.isNull(status)) {
            throw new OrderBusinessException(MessageConstant.ORDER_NOT_FOUND);
        }
        ordersMapper.delivery(id);
        return true;
    }

    //查看所有超时订单
    @Override
    public List<OrdersConfirmDTO> getTimeOutOrders() {
        return ordersMapper.getTimeOutOrders();
    }

    //查看所有运送中订单
    @Override
    public List<OrdersConfirmDTO> getTranprotOrders() {
        return ordersMapper.getTranprotOrders();
    }

    //每分钟轮询一遍查询是否有超时订单
    @Scheduled(fixedRate = 60 * 1000)
    public void pollingCancel() {
        List<OrdersConfirmDTO> ordersConfirmDTOS = ordersMapper.getTimeOutOrders();
        if (!ordersConfirmDTOS.isEmpty()) {
            for (OrdersConfirmDTO ordersConfirmDTO : ordersConfirmDTOS) {
                OrdersCancelDTO ordersCancelDTO = new OrdersCancelDTO();
                ordersCancelDTO.setId(ordersConfirmDTO.getId());
                ordersCancelDTO.setCancelReason(MessageConstant.ORDER_TIME_OUT);
                ordersMapper.cancel(ordersCancelDTO);
            }
        }
    }

    //凌晨一点是否有运送中的订单，如果有将其订单状态改成完成
    //cron表达式秒,分钟,小时,日,月,周 *表示任意,?不指定
    @Scheduled(cron = "0 0 1 * * ?")
    public void pollingComplete() {
        List<OrdersConfirmDTO> ordersConfirmDTOS = ordersMapper.getTranprotOrders();
        if (!ordersConfirmDTOS.isEmpty()) {
            for (OrdersConfirmDTO ordersConfirmDTO : ordersConfirmDTOS) {
                ordersMapper.completeOrder(ordersConfirmDTO.getId());
            }
        }
    }
}
