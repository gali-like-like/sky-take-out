package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * 套餐菜品关系(SetmealDish)表数据库访问层
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:52:47
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
     * 总记录数
     *
     * @param setmealDish 筛选条件
     * @return Long
     */
    Long count(SetmealDish setmealDish);

    /**
     * 查询指定行数据
     *
     * @param setmealDish 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<SetmealDish> queryAllByLimit(SetmealDish setmealDish, @Param("pageable") Pageable pageable);

    /**
     * 新增数据
     *
     * @param setmealDish 实例对象
     * @return 影响行数
     */
    int insert(SetmealDish setmealDish);

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

    // 查询全部
    @Select("SELECT * FROM setmeal_dish")
    List<SetmealDish> queryAll();

}

