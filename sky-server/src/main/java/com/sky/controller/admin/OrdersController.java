package com.sky.controller.admin;

import com.sky.dto.OrdersCancelDTO;
import com.sky.dto.OrdersConfirmDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersRejectionDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrdersService;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 订单表(Orders)表控制层
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:03
 */
@Api(tags = "订单管理接口")
@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService orderService;

    /**
     * 完成订单
     *
     * @param id 订单id
     * @return 完成订单结果
     */
    @PutMapping("/complete/{id}")
    @ApiOperation("完成订单")
    public Result<?> complete(@PathVariable("id") Long id) {
        int i = orderService.complete(id);
        if (i > 0) {
            return Result.success();
        }
        return Result.error("完成订单失败");
    }

    /**
     * 派送订单
     *
     * @param id 订单id
     * @return 派送订单结果
     */
    @PutMapping("/delivery/{id}")
    @ApiOperation("派送订单")
    public Result delivery(@PathVariable("id") Long id) {
        int i = orderService.delivery(id);
        if (i > 0) {
            return Result.success();
        }
        return Result.error("派送订单失败");
    }

    /**
     * 取消订单
     *
     * @param ordersCancelDTO 订单取消DTO
     * @return 取消订单结果
     */
    @PutMapping("/cancel")
    @ApiOperation("取消订单")
    public Result<?> cancel(@RequestBody OrdersCancelDTO ordersCancelDTO) throws Exception {
        int i = orderService.cancel(ordersCancelDTO);
        if (i > 0) {
            return Result.success();
        }
        return Result.error("取消订单失败");
    }


    /**
     * 拒单
     *
     * @param ordersRejectionDTO 订单拒单DTO
     * @return 拒单结果
     */
    @PutMapping("/rejection")
    @ApiOperation("拒单")
    public Result rejection(@RequestBody OrdersRejectionDTO ordersRejectionDTO) throws Exception {
        int i = orderService.rejection(ordersRejectionDTO);
        if (i > 0) {
            return Result.success();
        }
        return Result.error("拒单失败");
    }

    /**
     * 接单
     *
     * @param ordersConfirmDTO 订单接单DTO
     * @return 接单结果
     */
    @PutMapping("/confirm")
    @ApiOperation("接单")
    public Result confirm(@RequestBody OrdersConfirmDTO ordersConfirmDTO) {
        int i = orderService.confirm(ordersConfirmDTO);
        if (i > 0) {
            return Result.success();
        }
        return Result.error("接单失败");
    }

    /**
     * 订单详情
     *
     * @param id 订单id
     * @return  订单详情
     */
    @GetMapping("/details/{id}")
    @ApiOperation("查询订单详情")
    public Result<OrderVO> details(@PathVariable("id") Long id) {
        OrderVO orderVO = orderService.details(id);
        return Result.success(orderVO);
    }

    /**
     * 各个状态的订单数量统计
     *
     * @return 各个状态的订单数量统计
     */
    @GetMapping("/statistics")
    @ApiOperation("各个状态的订单数量统计")
    public Result<OrderStatisticsVO> statistics() {
        OrderStatisticsVO orderStatisticsVO = orderService.statistics();
        return Result.success(orderStatisticsVO);
    }

    /**
     * 订单搜索
     *
     * @param ordersPageQueryDTO 订单搜索DTO
     * @return 订单搜索结果
     */
    @GetMapping("/conditionSearch")
    @ApiOperation("订单搜索")
    public Result<PageResult> conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO) {
        PageResult pageResult = orderService.conditionSearch(ordersPageQueryDTO);
        return Result.success(pageResult);
    }

}

