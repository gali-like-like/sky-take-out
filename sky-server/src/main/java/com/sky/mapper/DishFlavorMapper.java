package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;


/**
 * 菜品口味关系表(DishFlavor)表数据库访问层
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:52:47
 */
@Mapper
public interface DishFlavorMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DishFlavor queryById(Long id);

    /**
     * 总记录数
     *
     * @param dishFlavor 筛选条件
     * @return Long
     */
    Long count(DishFlavor dishFlavor);

    /**
     * 查询指定行数据
     *
     * @param dishFlavor 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<DishFlavor> queryAllByLimit(DishFlavor dishFlavor, @Param("pageable") Pageable pageable);

    /**
     * 新增数据
     *
     * @param dishFlavor 实例对象
     * @return 影响行数
     */
    int insert(DishFlavor dishFlavor);

    /**
     * 修改数据
     *
     * @param dishFlavor 实例对象
     * @return 影响行数
     */
    int update(DishFlavor dishFlavor);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

