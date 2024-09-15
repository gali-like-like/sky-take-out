package com.sky.service;

import com.github.pagehelper.PageInfo;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 菜品及套餐分类(Category)表服务接口
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:04
 */
public interface CategoryService {

    //根据类型查询
    List<Category> queryByType(Long type);
    //添加分类
    int addCategory(CategoryDTO category);
    //更改状态
    Boolean changeStatus(Long id,Integer status);
    //修改分类
    Boolean updateCategory(CategoryDTO category);
    //根据id删除分类
    Boolean deleteCategoryById(Long id);
    //查询分页数据
    PageInfo<Category> queryPageCategory(CategoryPageQueryDTO queryDTO);
}
