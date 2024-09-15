package com.sky.service;

import com.sky.entity.AddressBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 地址簿(AddressBook)表服务接口
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:03
 */
public interface AddressBookService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AddressBook queryById(Long id);

    /**
     * 分页查询
     *
     * @param addressBook 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<AddressBook> queryByPage(AddressBook addressBook, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param addressBook 实例对象
     * @return 实例对象
     */
    AddressBook insert(AddressBook addressBook);

    /**
     * 修改数据
     *
     * @param addressBook 实例对象
     * @return 实例对象
     */
    AddressBook update(AddressBook addressBook);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
