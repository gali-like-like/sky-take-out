package com.sky.mapper;

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

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Category queryById(Long id);

    /**
     * 总记录数
     *
     * @param category 筛选条件
     * @return Long
     */
    Long count(Category category);

    /**
     * 查询指定行数据
     *
     * @param category 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Category> queryAllByLimit(Category category, @Param("pageable") Pageable pageable);

    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 影响行数
     */
    int insert(Category category);

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 影响行数
     */
    int update(Category category);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

