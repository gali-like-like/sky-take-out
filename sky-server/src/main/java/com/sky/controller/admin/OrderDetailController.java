package com.sky.controller.admin;

import com.sky.service.OrderDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订单明细表(OrderDetail)表控制层
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:00
 */
@RestController
@RequestMapping("admin/orderDetail")
public class OrderDetailController {
    /**
     * 服务对象
     */
    @Resource
    private OrderDetailService orderDetailService;


}

