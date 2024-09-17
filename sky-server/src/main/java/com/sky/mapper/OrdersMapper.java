package com.sky.mapper;

import com.sky.dto.GoodsSalesDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
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
            "where create_time >= #{beginTime} and create_time <= #{endTime} " +
            "and (status = #{status} or #{status} is null)")
    Integer getOrderCount(LocalDateTime beginTime, LocalDateTime endTime, Integer status);

    /**
     * 获取营业额
     *
     * @param begin
     * @param end
     * @return
     */
    @Select("select sum(total_amount) from orders " +
            "where order_time >= #{begin} and order_time <= #{end}" +
            "and (status = #{status} or #{status} is null)")
    Double getTurnover(LocalDateTime begin, LocalDateTime end, Integer status);

}

