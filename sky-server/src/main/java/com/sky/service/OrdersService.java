package com.sky.service;

import com.sky.dto.OrdersCancelDTO;
import com.sky.dto.OrdersConfirmDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersRejectionDTO;
import com.sky.result.PageResult;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;

import java.util.List;

/**
 * 订单表(Orders)表服务接口
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:03
 */
public interface OrdersService {
    //订单搜索
    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);
    //订单详情
    OrderVO details(Long id);
    //订单统计
    OrderStatisticsVO statistics();
    //完成订单
    Boolean completeOrder(Long orderId);
    //拒绝接单
    Boolean rejection(OrdersRejectionDTO ordersRejectionDTO);
    //取消订单
    Boolean cancel(OrdersCancelDTO ordersCancelDTO);
    //派送订单
    Boolean delivery(Long id);
    //查询超时订单
    List<OrdersConfirmDTO> getTimeOutOrders();
    //查询运送中订单
    public List<OrdersConfirmDTO> getTranprotOrders();
    //接单
    public Boolean confirm(Long orderId);
}
