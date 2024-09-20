package com.sky.controller.user;

import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@Api(tags={"订单接口"})
@RestController(" /user/order")
public class OrdersController {

    @ApiOperation(value="催单")
    @GetMapping("/reminder/{id}")
    public Result reminder(@ApiParam(name="id",defaultValue = "1",required = true) @PathVariable int id) {
        return Result.success();
    }

    @ApiOperation(value="再来一单")
    @PostMapping("/repetition/{id}")
    public Result repetition(@ApiParam(name="id",defaultValue = "1",required = true) @PathVariable int id) {
        return Result.success();
    }

    @ApiOperation(value="查询历史订单")
    @GetMapping("/historyOrders")
    public Result getHistoryOrders(OrdersPageQueryDTO ordersPageQueryDTO) {
        return Result.success();
    }

    @ApiOperation(value="取消订单")
    @PutMapping("/cancel/{id}")
    public Result cancelOrderById(@ApiParam(name="id",defaultValue = "1",required = true) @PathVariable int id) {
        return Result.success();
    }

    @ApiOperation(value="查询订单详情")
    @GetMapping("/orderDetail/{id}")
    public Result orderDetailById(@ApiParam(name="id",defaultValue = "1",required = true) @PathVariable int id) {
        return Result.success();
    }

    @ApiOperation(value="用户下单")
    @PostMapping("/submit")
    public Result submitOrder(@ApiParam(required=true) @RequestBody OrdersSubmitDTO ordersSubmitDTO) {
        return Result.success();
    }

    @ApiOperation(value = "订单支付")
    @PutMapping("/payment")
    public Result paymentOrder(@ApiParam(required=true) @RequestBody OrdersPaymentDTO ordersPaymentDTO) {
        return Result.success();
    }

}
