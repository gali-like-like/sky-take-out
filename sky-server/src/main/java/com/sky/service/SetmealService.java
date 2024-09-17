package com.sky.service;

import com.github.pagehelper.PageInfo;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;

import java.util.List;

/**
 * 套餐(Setmeal)表服务接口
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:02
 */
public interface SetmealService {
    //更新套餐
    public Boolean updateSetmeal(SetmealDTO setmeal);
    //分页查询
    public PageInfo<Setmeal> pageSetmeal(SetmealPageQueryDTO queryDTO);
    //更改套餐状态
    public Boolean updateSetmealStatus(Integer status, Long id);
    //批量删除套餐
    public Boolean deleteSetmals(List<Long> ids);
    //新增套餐
    public Long addSetmeal(SetmealDTO setmealDTO);
    //根据id查询套餐
    public SetmealDTO getSetmealById(Long id);
}
