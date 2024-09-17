package com.sky.service.impl;

import com.sky.constant.StatusConstant;
import com.sky.entity.Orders;
import com.sky.mapper.DishMapper;
import com.sky.mapper.OrdersMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.UserMapper;
import com.sky.service.WorkspaceService;
import com.sky.vo.BusinessDataVO;
import com.sky.vo.DishOverViewVO;
import com.sky.vo.OrderOverViewVO;
import com.sky.vo.SetmealOverViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 工作台相关接口
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-16 13:55:22
 */
@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {

    private final UserMapper userMapper;
    private final OrdersMapper ordersMapper;
    private final DishMapper dishMapper;
    private final SetmealMapper setmealMapper;

    // region 工作台数据的业务

    /**
     * 查询今日运营数据
     *
     * @return
     */
    @Override
    public BusinessDataVO getBusinessData() {
        //获得当天的开始时间
        LocalDateTime begin = LocalDateTime.now().with(LocalTime.MIN);
        //获得当天的结束时间
        LocalDateTime end = LocalDateTime.now().with(LocalTime.MAX);
        // 新增用户数
        Integer userCount = userMapper.getUserCount(begin, end);
        // 有效订单数
        Integer orderCount = ordersMapper.getOrderCount(begin, end, Orders.COMPLETED);
        // 有效订单数为0直接返回
        if (orderCount == 0){
            return BusinessDataVO.builder()
                    .newUsers(userCount)
                    .validOrderCount(orderCount)
                    .turnover(0.0)
                    .unitPrice(0.0)
                    .orderCompletionRate(0.0)
                    .build();
        }
        Integer allOrderCount = ordersMapper.getOrderCount(begin, end, null);
        // 订单完成率
        Double orderRate = (double) orderCount / allOrderCount * 100;
        // 营业额（一天内已完成订单的总金额）
        Double income = ordersMapper.getTurnover(begin, end, Orders.COMPLETED);
        // 平均客单价
        Double avgPrice = income / orderCount;

        return BusinessDataVO.builder()
                .newUsers(userCount)
                .validOrderCount(orderCount)
                .turnover(income)
                .unitPrice(avgPrice)
                .orderCompletionRate(orderRate)
                .build();
    }

    /**
     * 查询订单管理数据
     *
     * @return
     */
    @Override
    public OrderOverViewVO getOrderOverView() {
        //获得当天的开始时间
        LocalDateTime begin = LocalDateTime.now().with(LocalTime.MIN);
        //获得当天的结束时间
        LocalDateTime end = LocalDateTime.now().with(LocalTime.MAX);
        //待接单
        Integer waitingOrders = ordersMapper.getOrderCount(begin, end, Orders.TO_BE_CONFIRMED);
        //待派送
        Integer deliveredOrders = ordersMapper.getOrderCount(begin, end, Orders.CONFIRMED);
        //已完成
        Integer completedOrders = ordersMapper.getOrderCount(begin, end, Orders.COMPLETED);
        //已取消
        Integer cancelledOrders = ordersMapper.getOrderCount(begin, end, Orders.CANCELLED);
        //全部订单
        Integer allOrders = ordersMapper.getOrderCount(begin, end, null);
        return OrderOverViewVO.builder()
                .waitingOrders(waitingOrders)
                .deliveredOrders(deliveredOrders)
                .completedOrders(completedOrders)
                .cancelledOrders(cancelledOrders)
                .allOrders(allOrders)
                .build();
    }

    /**
     * 查询菜品总览
     *
     * @return
     */
    @Override
    public DishOverViewVO getDishOverView() {
        Integer sold = dishMapper.getDishCount(StatusConstant.ENABLE);
        Integer discontinued = dishMapper.getDishCount(StatusConstant.DISABLE);
        return DishOverViewVO.builder()
                .sold(sold)
                .discontinued(discontinued)
                .build();
    }

    /**
     * 查询套餐总览
     *
     * @return
     */
    @Override
    public SetmealOverViewVO getSetmealOverView() {
        Integer sold = setmealMapper.getSetmealCount(StatusConstant.ENABLE);
        Integer discontinued = setmealMapper.getSetmealCount(StatusConstant.DISABLE);
        return SetmealOverViewVO.builder()
                .sold(sold)
                .discontinued(discontinued)
                .build();
    }

    // endregion
}
