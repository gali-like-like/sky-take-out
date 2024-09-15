package com.sky.service;

import com.sky.entity.ShoppingCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 购物车(ShoppingCart)表服务接口
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:00
 */
public interface ShoppingCartService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ShoppingCart queryById(Long id);

    /**
     * 分页查询
     *
     * @param shoppingCart 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    Page<ShoppingCart> queryByPage(ShoppingCart shoppingCart, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param shoppingCart 实例对象
     * @return 实例对象
     */
    ShoppingCart insert(ShoppingCart shoppingCart);

    /**
     * 修改数据
     *
     * @param shoppingCart 实例对象
     * @return 实例对象
     */
    ShoppingCart update(ShoppingCart shoppingCart);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
