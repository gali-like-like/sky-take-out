package com.sky.mapper;

import com.sky.entity.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * 菜品(Dish)表数据库访问层
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:52:47
 */
@Mapper
public interface DishMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Dish queryById(Long id);

    /**
     * 总记录数
     *
     * @param dish 筛选条件
     * @return Long
     */
    Long count(Dish dish);

    /**
     * 查询指定行数据
     *
     * @param dish     查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Dish> queryAllByLimit(Dish dish, @Param("pageable") Pageable pageable);

    /**
     * 新增数据
     *
     * @param dish 实例对象
     * @return 影响行数
     */
    int insert(Dish dish);

    /**
     * 修改数据
     *
     * @param dish 实例对象
     * @return 影响行数
     */
    int update(Dish dish);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<Dish> getAllDish(@Param("categoryId") Integer categoryId, @Param("name") String name, @Param("status") Integer status);

    List<Dish> queryByCategoryId(Long categoryId);

    int updateStatus(Long id, Integer status);

    @Select("SELECT COUNT(id) FROM dish WHERE status = #{status}")
    Integer getDishCount(Integer status);

    //根据id查询菜品状态
    @Select("select status from dish where id = #{id}")
    Integer getStatusById(Long id);
}

