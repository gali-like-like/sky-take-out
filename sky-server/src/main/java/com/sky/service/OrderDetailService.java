package com.sky.service;

import com.sky.entity.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 订单明细表(OrderDetail)表服务接口
 *
 * @author makejava
 * @since 2024-09-15 10:00:02
 */
public interface OrderDetailService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OrderDetail queryById(Long id);

    /**
     * 分页查询
     *
     * @param orderDetail 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<OrderDetail> queryByPage(OrderDetail orderDetail, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param orderDetail 实例对象
     * @return 实例对象
     */
    OrderDetail insert(OrderDetail orderDetail);

    /**
     * 修改数据
     *
     * @param orderDetail 实例对象
     * @return 实例对象
     */
    OrderDetail update(OrderDetail orderDetail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
