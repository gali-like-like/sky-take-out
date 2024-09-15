package com.sky.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.exception.AccountNotFoundException;
import com.sky.mapper.CategoryMapper;
import com.sky.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 菜品及套餐分类(Category)表服务实现类
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:04
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    //根据类型查询
    @Override
    public List<Category> queryByType(Long type) {
        return categoryMapper.queryByType(type);
    }
    //添加分类
    @Override
    public int addCategory(CategoryDTO category) {
       return categoryMapper.addCategory(category);
    }
    //更改状态,id不存在就返回false,存在就修改
    @Override
    public Boolean changeStatus(Long id, Integer status) {
        Category category = categoryMapper.queryCategoryById(id);
        if(Objects.nonNull(category)) {
            categoryMapper.changeStatus(id, status);
            return true;
        }
        return false;
    }
    //修改分类,id不存在就false,否则修改
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Boolean updateCategory(CategoryDTO category) {
        Long id = category.getId();
        if(id < 0)
            throw new AccountNotFoundException();
        Category oldCategory = categoryMapper.queryCategoryById(id);
        if(Objects.nonNull(oldCategory)) {
            categoryMapper.updateCategory(category);
            return true;
        }
        throw new AccountNotFoundException();
    }
    //删除分类,根据id删除分类
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    @Override
    public Boolean deleteCategoryById(Long id) {
        if(id<0) {
            throw new AccountNotFoundException();
        }
       Category category = categoryMapper.queryCategoryById(id);
        if(Objects.nonNull(category)) {
            categoryMapper.deleteCategoryById(id);
            return true;
        }
        throw new AccountNotFoundException();
    }
    //查询分类数据
    @Override
    public PageInfo<Category> queryPageCategory(CategoryPageQueryDTO queryDTO) {
        List<Category> categoryList = categoryMapper.queryPageCategory(queryDTO);
        PageHelper.startPage(queryDTO.getPage(),queryDTO.getPageSize());
        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
        return pageInfo;
    }
}
