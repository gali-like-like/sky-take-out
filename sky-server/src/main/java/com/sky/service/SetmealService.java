package com.sky.service;

import com.sky.entity.Setmeal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 套餐(Setmeal)表服务接口
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:02
 */
public interface SetmealService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Setmeal queryById(Long id);

    /**
     * 分页查询
     *
     * @param setmeal     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Setmeal> queryByPage(Setmeal setmeal, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param setmeal 实例对象
     * @return 实例对象
     */
    Setmeal insert(Setmeal setmeal);

    /**
     * 修改数据
     *
     * @param setmeal 实例对象
     * @return 实例对象
     */
    Setmeal update(Setmeal setmeal);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
