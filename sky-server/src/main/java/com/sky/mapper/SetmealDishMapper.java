package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;


/**
 * 套餐菜品关系(SetmealDish)表数据库访问层
 *
 * @author makejava
 * @since 2024-09-15 10:14:31
 */
@Mapper
public interface SetmealDishMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SetmealDish queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param setmealDish 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<SetmealDish> queryAllByLimit(SetmealDish setmealDish, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param setmealDish 查询条件
     * @return 总行数
     */
    long count(SetmealDish setmealDish);

    /**
     * 新增数据
     *
     * @param setmealDish 实例对象
     * @return 影响行数
     */
    int insert(SetmealDish setmealDish);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SetmealDish> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SetmealDish> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SetmealDish> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SetmealDish> entities);

    /**
     * 修改数据
     *
     * @param setmealDish 实例对象
     * @return 影响行数
     */
    int update(SetmealDish setmealDish);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

