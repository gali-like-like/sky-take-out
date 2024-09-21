package com.sky.mapper;

import com.sky.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * 地址簿(AddressBook)表数据库访问层
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:52:47
 */
@Mapper
public interface AddressBookMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AddressBook queryById(Long id);

    /**
     * 总记录数
     *
     * @param addressBook 筛选条件
     * @return Long
     */
    Long count(AddressBook addressBook);

    /**
     * 查询指定行数据
     *
     * @param addressBook 查询条件
     * @param pageable    分页对象
     * @return 对象列表
     */
    List<AddressBook> queryAllByLimit(AddressBook addressBook, @Param("pageable") Pageable pageable);

    /**
     * 新增数据
     *
     * @param addressBook 实例对象
     * @return 影响行数
     */
    int insert(AddressBook addressBook);

    /**
     * 修改数据
     *
     * @param addressBook 实例对象
     * @return 影响行数
     */
    int update(AddressBook addressBook);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<AddressBook> queryAll();

    @Select("select * from address_book where is_default = 1 and user_id = #{userId}")
    AddressBook queryDefaultAddress(Long userId);

    @Update("update address_book set is_default = 1 where id = #{addressId}")
    int setDefaultAddress(Long userId);
}

