package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;


/**
 * 购物车(ShoppingCart)表数据库访问层
 *
 * @author makejava
 * @since 2024-09-15 10:14:29
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
     * 查询指定行数据
     *
     * @param shoppingCart 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<ShoppingCart> queryAllByLimit(ShoppingCart shoppingCart, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param shoppingCart 查询条件
     * @return 总行数
     */
    long count(ShoppingCart shoppingCart);

    /**
     * 新增数据
     *
     * @param shoppingCart 实例对象
     * @return 影响行数
     */
    int insert(ShoppingCart shoppingCart);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ShoppingCart> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ShoppingCart> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ShoppingCart> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ShoppingCart> entities);

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

