package com.sky.service.impl;

import com.sky.entity.Setmeal;
import com.sky.mapper.SetmealMapper;
import com.sky.service.SetmealService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 套餐(Setmeal)表服务实现类
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:02
 */
@Service("setmealService")
public class SetmealServiceImpl implements SetmealService {
    @Resource
    private SetmealMapper setmealMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Setmeal queryById(Long id) {
        return setmealMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param setmeal     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Setmeal> queryByPage(Setmeal setmeal, PageRequest pageRequest) {
        long total = setmealMapper.count(setmeal);
        return new PageImpl<>(setmealMapper.queryAllByLimit(setmeal, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param setmeal 实例对象
     * @return 实例对象
     */
    @Override
    public Setmeal insert(Setmeal setmeal) {
        setmealMapper.insert(setmeal);
        return setmeal;
    }

    /**
     * 修改数据
     *
     * @param setmeal 实例对象
     * @return 实例对象
     */
    @Override
    public Setmeal update(Setmeal setmeal) {
        setmealMapper.update(setmeal);
        return queryById(setmeal.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return setmealMapper.deleteById(id) > 0;
    }
}
