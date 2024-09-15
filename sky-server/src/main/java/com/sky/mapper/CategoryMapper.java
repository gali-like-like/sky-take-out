package com.sky.mapper;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;


/**
 * 菜品及套餐分类(Category)表数据库访问层
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:52:47
 */
@Mapper
public interface CategoryMapper {
    //根据类型查询
    List<Category> queryByType(Long type);
    //添加分类
    int addCategory(CategoryDTO category);
    //更改状态
    void changeStatus(Long id,Integer status);
    //修改分类
    int updateCategory(CategoryDTO category);
    //根据id删除分类
    int deleteCategoryById(Long id);
    //查询分页数据
    List<Category>  queryPageCategory(CategoryPageQueryDTO queryDTO);
    //根据id查询分类
    Category queryCategoryById(Long id);
}

