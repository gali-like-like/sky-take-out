package com.sky.service;

import com.sky.dto.OrdersCancelDTO;
import com.sky.dto.OrdersConfirmDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersRejectionDTO;
import com.sky.result.PageResult;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;

/**
 * 订单表(Orders)表服务接口
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:03
 */
public interface OrdersService {

    /**
     * 订单搜索
     *
     * @param ordersPageQueryDTO 订单搜索条件
     * @return 分页结果
     */
    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 订单详情
     *
     * @param id 订单id
     * @return 订单详情
     */
    OrderVO details(Long id);

    /**
     * 订单统计
     *
     * @return 订单统计
     */
    OrderStatisticsVO statistics();

    /**
     * 订单确认
     *
     * @param ordersConfirmDTO 订单确认条件
     * @return 订单确认
     */
    int confirm(OrdersConfirmDTO ordersConfirmDTO);

    /**
     * 订单拒绝
     *
     * @param ordersRejectionDTO 订单拒绝条件
     * @return 订单拒绝
     */
    int rejection(OrdersRejectionDTO ordersRejectionDTO);

    /**
     * 订单取消
     *
     * @param ordersCancelDTO 订单取消条件
     * @return 订单取消
     */
    int cancel(OrdersCancelDTO ordersCancelDTO);

    /**
     * 订单发货
     *
     * @param id 订单id
     * @return 订单发货
     */
    int delivery(Long id);

    /**
     * 订单完成
     *
     * @param id 订单id
     * @return 订单完成
     */
    int complete(Long id);
}
