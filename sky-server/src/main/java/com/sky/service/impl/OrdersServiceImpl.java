package com.sky.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.convert.OrderConvert;
import com.sky.dto.OrdersCancelDTO;
import com.sky.dto.OrdersConfirmDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersRejectionDTO;
import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import com.sky.mapper.OrderDetailMapper;
import com.sky.mapper.OrdersMapper;
import com.sky.result.PageResult;
import com.sky.service.OrdersService;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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


    /**
     * 订单搜索条件
     *
     * @param ordersPageQueryDTO 订单搜索条件
     * @return
     */
    @Override
    public PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO) {
        PageHelper.startPage(ordersPageQueryDTO.getPage(), ordersPageQueryDTO.getPageSize());
        List<Orders> orders = orderMapper.pageQuery(ordersPageQueryDTO);
        List<OrderVO> collect = orders.stream()
                .map(order -> {
                    OrderVO orderVO = new OrderVO();
                    OrderConvert.INSTANCE.orderToOrderVo(order,orderVO);
                    return orderVO;
                })
                .collect(Collectors.toList());
        PageInfo<OrderVO> pageInfo = new PageInfo<>(collect);
        return new PageResult(pageInfo.getTotal(), collect);
    }

    /**
     * 订单详情
     *
     * @param id 订单id
     * @return 订单详情
     */
    @Override
    public OrderVO details(Long id) {
        Orders orders = orderMapper.getById(id);
        List<OrderDetail> orderDetailList = orderDetailMapper.getByOrderId(orders.getId());
        OrderVO orderVO = new OrderVO();
        OrderConvert.INSTANCE.orderToOrderVo(orders,orderVO);
        orderVO.setOrderDetailList(orderDetailList);
        return orderVO;
    }

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

    // region 待实现的接口

    @Override
    public int confirm(OrdersConfirmDTO ordersConfirmDTO) {
        return 0;
    }

    @Override
    public int rejection(OrdersRejectionDTO ordersRejectionDTO) {
        return 0;
    }

    @Override
    public int cancel(OrdersCancelDTO ordersCancelDTO) {
        return 0;
    }

    @Override
    public int delivery(Long id) {
        return 0;
    }

    @Override
    public int complete(Long id) {
        return 0;
    }

    // endregion
}
