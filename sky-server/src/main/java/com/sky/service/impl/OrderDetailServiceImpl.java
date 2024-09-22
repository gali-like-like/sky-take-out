package com.sky.service.impl;

import com.sky.entity.OrderDetail;
import com.sky.mapper.OrderDetailMapper;
import com.sky.service.OrderDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单明细表(OrderDetail)表服务实现类
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:00
 */
@Service("orderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService {
    @Resource
    private OrderDetailMapper orderDetailMapper;

    /**
     * 通过订单id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public List<OrderDetail> getOrderDetailByOrderId(Long id) {
        return orderDetailMapper.getOrderDetailByOrderId(id);
    };
    /**
     * 添加订单详情
     *
     * */
    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetailMapper.addOrderDetail(orderDetail);
    };

}
