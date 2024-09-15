package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;


/**
 * 菜品口味关系表(DishFlavor)表数据库访问层
 *
 * @author makejava
 * @since 2024-09-15 10:14:29
 */
@Mapper
public interface DishFlavorMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DishFlavor queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param dishFlavor 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<DishFlavor> queryAllByLimit(DishFlavor dishFlavor, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param dishFlavor 查询条件
     * @return 总行数
     */
    long count(DishFlavor dishFlavor);

    /**
     * 新增数据
     *
     * @param dishFlavor 实例对象
     * @return 影响行数
     */
    int insert(DishFlavor dishFlavor);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<DishFlavor> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<DishFlavor> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<DishFlavor> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<DishFlavor> entities);

    /**
     * 修改数据
     *
     * @param dishFlavor 实例对象
     * @return 影响行数
     */
    int update(DishFlavor dishFlavor);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

