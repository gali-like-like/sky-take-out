package com.sky.mapper;

import com.sky.entity.Setmeal;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;


/**
 * 套餐(Setmeal)表数据库访问层
 *
 * @author makejava
 * @since 2024-09-15 10:14:26
 */
@Mapper
public interface SetmealMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Setmeal queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param setmeal 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Setmeal> queryAllByLimit(Setmeal setmeal, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param setmeal 查询条件
     * @return 总行数
     */
    long count(Setmeal setmeal);

    /**
     * 新增数据
     *
     * @param setmeal 实例对象
     * @return 影响行数
     */
    int insert(Setmeal setmeal);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Setmeal> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Setmeal> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Setmeal> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Setmeal> entities);

    /**
     * 修改数据
     *
     * @param setmeal 实例对象
     * @return 影响行数
     */
    int update(Setmeal setmeal);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

