package com.sky.service;

import com.sky.entity.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 菜品(Dish)表服务接口
 *
 * @author makejava
 * @since 2024-09-15 10:00:01
 */
public interface DishService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Dish queryById(Long id);

    /**
     * 分页查询
     *
     * @param dish        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Dish> queryByPage(Dish dish, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param dish 实例对象
     * @return 实例对象
     */
    Dish insert(Dish dish);

    /**
     * 修改数据
     *
     * @param dish 实例对象
     * @return 实例对象
     */
    Dish update(Dish dish);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
