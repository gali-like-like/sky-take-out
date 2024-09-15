package com.sky.mapper;

import com.sky.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * 地址簿(AddressBook)表数据库访问层
 *
 * @author makejava
 * @since 2024-09-15 10:14:27
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
     * 查询指定行数据
     *
     * @param addressBook 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<AddressBook> queryAllByLimit(AddressBook addressBook, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param addressBook 查询条件
     * @return 总行数
     */
    long count(AddressBook addressBook);

    /**
     * 新增数据
     *
     * @param addressBook 实例对象
     * @return 影响行数
     */
    int insert(AddressBook addressBook);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AddressBook> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AddressBook> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AddressBook> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<AddressBook> entities);

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

}

