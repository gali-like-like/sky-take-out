package com.sky.service.impl;

import com.sky.entity.Dish;
import com.sky.mapper.DishMapper;
import com.sky.service.DishService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 菜品(Dish)表服务实现类
 *
 * @author makejava
 * @since 2024-09-15 10:00:01
 */
@Service("dishService")
public class DishServiceImpl implements DishService {
    @Resource
    private DishMapper dishMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Dish queryById(Long id) {
        return this.dishMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param dish        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Dish> queryByPage(Dish dish, PageRequest pageRequest) {
        long total = this.dishMapper.count(dish);
        return new PageImpl<>(this.dishMapper.queryAllByLimit(dish, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param dish 实例对象
     * @return 实例对象
     */
    @Override
    public Dish insert(Dish dish) {
        this.dishMapper.insert(dish);
        return dish;
    }

    /**
     * 修改数据
     *
     * @param dish 实例对象
     * @return 实例对象
     */
    @Override
    public Dish update(Dish dish) {
        this.dishMapper.update(dish);
        return this.queryById(dish.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.dishMapper.deleteById(id) > 0;
    }
}
