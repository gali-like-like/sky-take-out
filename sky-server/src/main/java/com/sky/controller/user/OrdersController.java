package com.sky.controller.user;

import com.github.pagehelper.PageInfo;
import com.sky.constant.MessageConstant;
import com.sky.dto.*;
import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import com.sky.result.Result;
import com.sky.service.OrderDetailService;
import com.sky.service.OrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Api(tags={"订单接口"})
@RestController(" /user/order")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrderDetailService orderDetailService;

    @ApiOperation(value="催单")
    @GetMapping("/reminder/{id}")
    public Result reminder(@ApiParam(name="id",defaultValue = "1",required = true) @PathVariable Long id) {
        ordersService.reminders(id);
        return Result.success();
    }

    @ApiOperation(value="再来一单")
    @PostMapping("/repetition/{id}")
    public Result repetition(@ApiParam(name="id",defaultValue = "1",required = true) @PathVariable Long id) {
        ordersService.addOrder(id);
        return Result.success();
    }

    @ApiOperation(value="查询历史订单")
    @GetMapping("/historyOrders")
    public Result getHistoryOrders(OrdersHistoryPageQueryDTO ordersPageQueryDTO) {
        PageInfo<Orders> pageData = ordersService.getOrderByUserIdAndStatus(ordersPageQueryDTO);
        return Result.success(pageData);
    }

    @ApiOperation(value="取消订单")
    @PutMapping("/cancel/{id}")
    public Result cancelOrderById(@ApiParam(required = true)OrdersCancelDTO ordersCancelDTO) {
        ordersService.cancel(ordersCancelDTO);
        return Result.success();
    }

    @ApiOperation(value="查询订单详情")
    @GetMapping("/orderDetail/{id}")
    public Result orderDetailById(@ApiParam(name="id",defaultValue = "1",required = true) @PathVariable Long id) {
        List<OrderDetail> orderDetails = orderDetailService.getOrderDetailByOrderId(id);
        return Objects.isNull(orderDetails)?Result.success(orderDetails):Result.error(MessageConstant.ORDER_NOT_FOUND);
    }

    @ApiOperation(value="用户下单")
    @PostMapping("/submit")
    public Result submitOrder(@ApiParam(required=true) @RequestBody OrdersDTO ordersDTO) {
        ordersService.placeOrder(ordersDTO);
        return Result.success();
    }

    @ApiOperation(value = "订单支付")
    @PutMapping("/payment")
    public Result paymentOrder(@ApiParam(required=true) @RequestBody OrdersPaymentDTO ordersPaymentDTO) {
        ordersService.orderPay(ordersPaymentDTO);
        return Result.success();
    }

}
