package com.sky.service;

import com.sky.entity.OrderDetail;

import java.util.List;

/**
 * 订单明细表(OrderDetail)表服务接口
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:00
 */
public interface OrderDetailService {

    /**
     * 通过订单id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    List<OrderDetail> getOrderDetailByOrderId(Long id);
    /**
     * 添加订单详情
     *
     * */
    void addOrderDetail(OrderDetail orderDetail);

}
