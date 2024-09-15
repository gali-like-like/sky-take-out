package com.sky.service.impl;

import com.sky.entity.SetmealDish;
import com.sky.mapper.SetmealDishMapper;
import com.sky.service.SetmealDishService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 套餐菜品关系(SetmealDish)表服务实现类
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:02
 */
@Service("setmealDishService")
public class SetmealDishServiceImpl implements SetmealDishService {
    @Resource
    private SetmealDishMapper setmealDishMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SetmealDish queryById(Long id) {
        return setmealDishMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param setmealDish 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<SetmealDish> queryByPage(SetmealDish setmealDish, PageRequest pageRequest) {
        long total = setmealDishMapper.count(setmealDish);
        return new PageImpl<>(setmealDishMapper.queryAllByLimit(setmealDish, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param setmealDish 实例对象
     * @return 实例对象
     */
    @Override
    public SetmealDish insert(SetmealDish setmealDish) {
        setmealDishMapper.insert(setmealDish);
        return setmealDish;
    }

    /**
     * 修改数据
     *
     * @param setmealDish 实例对象
     * @return 实例对象
     */
    @Override
    public SetmealDish update(SetmealDish setmealDish) {
        setmealDishMapper.update(setmealDish);
        return queryById(setmealDish.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return setmealDishMapper.deleteById(id) > 0;
    }
}
