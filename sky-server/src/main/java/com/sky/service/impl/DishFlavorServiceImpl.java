package com.sky.service.impl;

import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorMapper;
import com.sky.service.DishFlavorService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 菜品口味关系表(DishFlavor)表服务实现类
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:01
 */
@Service("dishFlavorService")
public class DishFlavorServiceImpl implements DishFlavorService {
    @Resource
    private DishFlavorMapper dishFlavorMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DishFlavor queryById(Long id) {
        return dishFlavorMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param dishFlavor  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<DishFlavor> queryByPage(DishFlavor dishFlavor, PageRequest pageRequest) {
        long total = dishFlavorMapper.count(dishFlavor);
        return new PageImpl<>(dishFlavorMapper.queryAllByLimit(dishFlavor, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param dishFlavor 实例对象
     * @return 实例对象
     */
    @Override
    public DishFlavor insert(DishFlavor dishFlavor) {
        dishFlavorMapper.insert(dishFlavor);
        return dishFlavor;
    }

    /**
     * 修改数据
     *
     * @param dishFlavor 实例对象
     * @return 实例对象
     */
    @Override
    public DishFlavor update(DishFlavor dishFlavor) {
        dishFlavorMapper.update(dishFlavor);
        return queryById(dishFlavor.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return dishFlavorMapper.deleteById(id) > 0;
    }
}
