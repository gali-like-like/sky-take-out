package com.sky.service.impl;

import com.sky.mapper.OrderDetailMapper;
import com.sky.service.OrderDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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


}
