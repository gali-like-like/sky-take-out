package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.dto.OrdersCancelDTO;
import com.sky.dto.OrdersConfirmDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersRejectionDTO;
import com.sky.future.MakeUpFuture;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrdersService;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import io.netty.util.concurrent.CompleteFuture;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * 订单表(Orders)表控制层
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:03
 */
@RestController
@RequestMapping("/admin/order")
@RequiredArgsConstructor
public class OrdersController {
    @Autowired
    private final OrdersService orderService;
    @Autowired
    private MakeUpFuture makeUpFuture;

    @PutMapping("/complete/{id}")
    @ApiOperation("完成订单")
    public Result<?> complete(@PathVariable("id") Long id) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(()->{
           return orderService.completeOrder(id);
        }).handle((res,e)->{
            return (Boolean) makeUpFuture.makeUpHandle(res,e);
        });
        return makeUpFuture.makeUpBoolFuture(
                future,
                MessageConstant.ORDER_COMPLETE_SUCCESS,
                MessageConstant.UNKNOWN_ERROR,
                MessageConstant.ORDER_NOT_FOUND);
    }

    @PutMapping("/delivery/{id}")
    @ApiOperation(value = "派送订单",notes="根据id派送订单")
    public Result delivery(@ApiParam(name="id",required = true) @PathVariable("id") Long id) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Boolean> future = CompletableFuture
                .supplyAsync(
                        ()->orderService.delivery(id))
                .handle((res,e)->(Boolean)makeUpFuture.makeUpHandle(res,e));
        return makeUpFuture.makeUpBoolFuture(
                future,
                MessageConstant.ORDER_DELIVERY_SUCCESS,
                MessageConstant.UNKNOWN_ERROR,
                MessageConstant.ORDER_NOT_FOUND
        );
    }

    @PutMapping("/cancel")
    @ApiOperation("取消订单")
    public Result<?> cancel(@ApiParam(required = true) @RequestBody OrdersCancelDTO ordersCancelDTO) throws Exception {
        CompletableFuture<Boolean> future = CompletableFuture
                .supplyAsync(
                        ()->orderService.cancel(ordersCancelDTO))
                .handle((res,e)->(Boolean)makeUpFuture.makeUpHandle(res,e));
        return makeUpFuture.makeUpBoolFuture(
                future,
                MessageConstant.ORDER_CANCEL_SUCCESS,
                MessageConstant.UNKNOWN_ERROR,
                MessageConstant.ORDER_NOT_FOUND
        );
    }

    @PutMapping("/rejection")
    @ApiOperation(value="拒单",notes="根据拒单原因拒绝某单")
    public Result rejection(@ApiParam(required = true) @RequestBody OrdersRejectionDTO ordersRejectionDTO) throws Exception {
        CompletableFuture<Boolean> future = CompletableFuture
                .supplyAsync(
                        ()->orderService.rejection(ordersRejectionDTO))
                .handle((res,e)->(Boolean)makeUpFuture.makeUpHandle(res,e));
        return makeUpFuture.makeUpBoolFuture(
                future,
                MessageConstant.ORDER_REJECT_SUCCESS,
                MessageConstant.UNKNOWN_ERROR,
                MessageConstant.ORDER_NOT_FOUND
        );
    }

    @PutMapping("/confirm")
    @ApiOperation("接单")
    public Result confirm(@ApiParam(name="id",required = true) @RequestBody Long id) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Boolean> future = CompletableFuture
                .supplyAsync(
                        ()->orderService.confirm(id))
                .handle((res,e)->(Boolean)makeUpFuture.makeUpHandle(res,e));
        return makeUpFuture.makeUpBoolFuture(
                future,
                MessageConstant.ORDER_GET_SUCCESS,
                MessageConstant.UNKNOWN_ERROR,
                MessageConstant.ORDER_NOT_FOUND
        );
    }

    @GetMapping("/details/{id}")
    @ApiOperation(value="查询订单详情",notes="根据id查询订单详情")
    public Result<OrderVO> details(@ApiParam(name="id",required = true)@PathVariable("id") Long id) {
        OrderVO orderVO = orderService.details(id);
        return Result.success(orderVO);
    }

    @GetMapping("/statistics")
    @ApiOperation("各个状态的订单数量统计")
    public Result<OrderStatisticsVO> statistics() {
        OrderStatisticsVO orderStatisticsVO = orderService.statistics();
        return Result.success(orderStatisticsVO);
    }

    @GetMapping("/conditionSearch")
    @ApiOperation("订单搜索")
    public Result<PageResult> conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO) {
        PageResult pageResult = orderService.conditionSearch(ordersPageQueryDTO);
        return Result.success(pageResult);
    }
}

