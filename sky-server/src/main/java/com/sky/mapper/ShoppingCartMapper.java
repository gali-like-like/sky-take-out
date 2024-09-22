package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * 购物车(ShoppingCart)表数据库访问层
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:52:47
 */
@Mapper
public interface ShoppingCartMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ShoppingCart queryById(Long id);

    /**
     * 总记录数
     *
     * @param shoppingCart 筛选条件
     * @return Long
     */
    Long count(ShoppingCart shoppingCart);

    /**
     * 查询指定行数据
     *
     * @param shoppingCart 查询条件
     * @param pageable     分页对象
     * @return 对象列表
     */
    List<ShoppingCart> queryAllByLimit(ShoppingCart shoppingCart, @Param("pageable") Pageable pageable);

    /**
     * 新增数据
     *
     * @param shoppingCart 实例对象
     * @return 影响行数
     */
    int insert(ShoppingCart shoppingCart);

    /**
     * 修改数据
     *
     * @param shoppingCart 实例对象
     * @return 影响行数
     */
    int update(ShoppingCart shoppingCart);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

