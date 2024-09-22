package com.sky.mapper;

import com.sky.dto.*;
import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;


/**
 * 订单表(Orders)表数据库访问层
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:52:47
 */
@Mapper
public interface OrdersMapper {

    /**
     * 分页查询订单列表
     *
     * @param ordersPageQueryDTO 订单分页查询条件
     * @return 分页查询结果
     */
    List<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 根据id查询订单
     *
     * @param id 订单id
     * @return 订单
     */
    Orders getById(Long id);

    /**
     * 根据状态统计订单数量
     *
     * @param status
     */
    @Select("select count(id) from orders where status = #{status}")
    Integer countStatus(Integer status);

    /**
     * 获取销售排行榜前10的商品
     *
     * @param beginTime
     * @param endTime
     * @return 商品销售排行榜
     */
    List<GoodsSalesDTO> getSalesTop10(LocalDateTime beginTime, LocalDateTime endTime);

    /**
     * 获取订单数量
     *
     * @param beginTime
     * @param endTime
     * @param status
     * @return
     */
    @Select("select count(id) from orders " +
            "where  order_time>= #{beginTime} and order_time <= #{endTime} " +
            "and (status = #{status} or #{status} is null)")
    Integer getOrderCount(LocalDateTime beginTime, LocalDateTime endTime, Integer status);

    /**
     * 获取营业额
     *
     * @param begin
     * @param end
     * @return
     */
    @Select("select sum(amount) from orders " +
            "where order_time >= #{begin} and order_time <= #{end}" +
            "and (status = #{status} or #{status} is null)")
    Double getTurnover(LocalDateTime begin, LocalDateTime end, Integer status);

    //确认订单，完成订单
    public void completeOrder(Long orderId);

    //查询订单状态
    public Integer getStatusById(Long orderId);

    //查询超时订单
    public List<OrdersConfirmDTO> getTimeOutOrders();

    //查询运送中的订单
    public List<OrdersConfirmDTO> getTranprotOrders();

    //取消订单
    public void cancel(OrdersCancelDTO ordersCancelDTO);

    //拒绝订单
    public void reject(OrdersRejectionDTO ordersRejectionDTO);

    //派送订单
    public void delivery(Long orderId);

    //接单
    public void confirm(Long orderId);

    //查询用户最近完成的一单信息
    public Orders getLastCompleteOrderById(Long userId);

    //历史订单查询
    public List<Orders> getOrderByUserIdAndStatus(OrdersHistoryPageQueryDTO ordersHistoryPageQueryDTO);

    //添加订单信息
    @Options(useGeneratedKeys = true,keyColumn = "id")
    public Long addOrder(OrdersDTO ordersDTO);

    //订单支付
    public void orderPay(OrdersPaymentDTO ordersPaymentDTO);

    //根据id查询用户id
    @Select("select user_id from orders where id = #{id}")
    public Long getUserIdById(Long id);

    //根据id查询订单号
    @Select("select number from orders where id = #{id}")
    public String getNumberById(Long id);
}

