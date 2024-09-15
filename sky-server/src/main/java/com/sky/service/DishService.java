package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.vo.DishVO;

import java.util.HashMap;
import java.util.List;

/**
 * 菜品(Dish)表服务接口
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:03
 */
public interface DishService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DishVO queryById(Long id);

    /**
     * 分页查询
     *
     * @param dishPageQueryDTO 筛选条件
     * @return 查询结果
     */
    HashMap<String, Object> queryByPage(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 新增数据
     *
     * @param dish 实例对象
     * @return 实例对象
     */
    Boolean insert(Dish dish);

    /**
     * 修改数据
     *
     * @param dish 实例对象
     * @return 实例对象
     */
    Boolean update(DishDTO dish);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    Boolean deleteByIds(String ids);

    List<DishVO> queryByCategoryId(Long categoryId);

    Boolean updateStatus(Long id, Integer status);
}
