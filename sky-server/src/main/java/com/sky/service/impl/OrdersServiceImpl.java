package com.sky.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.command.MapInitializer;
import com.sky.constant.MessageConstant;
import com.sky.convert.OrderConvert;
import com.sky.dto.OrdersCancelDTO;
import com.sky.dto.OrdersConfirmDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersRejectionDTO;
import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import com.sky.exception.OrderBusinessException;
import com.sky.mapper.OrderDetailMapper;
import com.sky.mapper.OrdersMapper;
import com.sky.result.PageResult;
import com.sky.service.OrdersService;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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
    private final OrdersMapper orderMapper;
    private final OrderDetailMapper orderDetailMapper;
    private final OrdersMapper ordersMapper;
    private final MapInitializer mapInitializer;
    //订单搜索条件
    @Override
    public PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO) {
        PageHelper.startPage(ordersPageQueryDTO.getPage(), ordersPageQueryDTO.getPageSize());
        List<Orders> orders = orderMapper.pageQuery(ordersPageQueryDTO);
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
        Orders orders = orderMapper.getById(id);
        List<OrderDetail> orderDetailList = orderDetailMapper.getByOrderId(orders.getId());
        OrderVO orderVO = new OrderVO();
        OrderConvert.INSTANCE.orderToOrderVo(orders, orderVO);
        orderVO.setOrderDetailList(orderDetailList);
        return orderVO;
    }
    //统计
    @Override
    public OrderStatisticsVO statistics() {
        // 根据状态，分别查询出待接单、待派送、派送中的订单数量
        Integer toBeConfirmed = orderMapper.countStatus(Orders.TO_BE_CONFIRMED);
        Integer confirmed = orderMapper.countStatus(Orders.CONFIRMED);
        Integer deliveryInProgress = orderMapper.countStatus(Orders.DELIVERY_IN_PROGRESS);

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
        orderMapper.completeOrder(orderId);
        return true;
    }
    //拒绝接单
    @Override
    public Boolean rejection(OrdersRejectionDTO ordersRejectionDTO) {
        Long id = ordersRejectionDTO.getId();
        Orders order = orderMapper.getById(id);
        if(Objects.isNull(order))
            throw new OrderBusinessException(MessageConstant.ORDER_NOT_FOUND);
        orderMapper.reject(ordersRejectionDTO);
        return true;
    }
    //取消订单
    @Override
    public Boolean cancel(OrdersCancelDTO ordersCancelDTO) {
        Integer status = orderMapper.getStatusById(ordersCancelDTO.getId());
        if(Objects.isNull(status)) {
            throw new OrderBusinessException(MessageConstant.ORDER_NOT_FOUND);
        }
        orderMapper.cancel(ordersCancelDTO);
        return true;
    }
    //接单
    public Boolean confirm(Long orderId) {
        Orders orders = ordersMapper.getById(orderId);
        if(Objects.isNull(orders)) {
            throw new OrderBusinessException(MessageConstant.ORDER_NOT_FOUND);
        }
        orderMapper.confirm(orderId);
        return true;
    }

    //派送订单
    @Override
    public Boolean delivery(Long id) {
        Integer status = orderMapper.getStatusById(id);
        if(Objects.isNull(status)) {
            throw new OrderBusinessException(MessageConstant.ORDER_NOT_FOUND);
        }
        orderMapper.delivery(id);
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
    @Scheduled(fixedRate = 60*1000)
    public void pollingCancel() {
        List<OrdersConfirmDTO> ordersConfirmDTOS = ordersMapper.getTimeOutOrders();
        if(!ordersConfirmDTOS.isEmpty()) {
            for (OrdersConfirmDTO ordersConfirmDTO : ordersConfirmDTOS) {
                OrdersCancelDTO ordersCancelDTO = new OrdersCancelDTO();
                ordersCancelDTO.setId(ordersConfirmDTO.getId());
                ordersCancelDTO.setCancelReason(MessageConstant.ORDER_TIME_OUT);
                orderMapper.cancel(ordersCancelDTO);
            }
        }
    }

    //凌晨一点是否有运送中的订单，如果有将其订单状态改成完成
    @Scheduled(cron = "0 1 * * * ?")
    public void pollingComplete() {
        List<OrdersConfirmDTO> ordersConfirmDTOS = ordersMapper.getTranprotOrders();
        if(!ordersConfirmDTOS.isEmpty()) {
            for (OrdersConfirmDTO ordersConfirmDTO : ordersConfirmDTOS) {
                orderMapper.completeOrder(ordersConfirmDTO.getId());
            }
        }
    }
}
