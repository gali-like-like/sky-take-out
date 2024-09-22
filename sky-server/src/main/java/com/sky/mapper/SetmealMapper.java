package com.sky.mapper;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * 套餐(Setmeal)表数据库访问层
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:52:47
 */
@Mapper
public interface SetmealMapper {
    //更新套餐
    public void updateSetmeal(SetmealDTO setmeal);

    //分页查询
    public List<Setmeal> pageSetmeal(SetmealPageQueryDTO queryDTO);

    //更改套餐状态
    public void updateSetmealStatus(Integer status, Long id);

    //批量删除套餐
    public void deleteSetmals(List<Long> ids);

    //新增套餐
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public Long addSetmeal(SetmealDTO setmealDTO);

    //根据id查询套餐
    public Setmeal getSetmealById(Long id);
    //查看状态下的数量
    @Select("SELECT COUNT(id) FROM setmeal WHERE status = #{status}")
    Integer getSetmealCount(Integer status);

    List<Setmeal> getSetmealByCategoryId(Long categoryId);
    //根据id查看套餐状态
    @Select("select status from setmeaml where id = #{id}")
    Integer getStatusById(Long id);

}

