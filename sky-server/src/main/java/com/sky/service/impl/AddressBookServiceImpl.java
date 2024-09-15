package com.sky.service.impl;

import com.sky.entity.AddressBook;
import com.sky.mapper.AddressBookMapper;
import com.sky.service.AddressBookService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 地址簿(AddressBook)表服务实现类
 *
 * @author makejava
 * @since 2024-09-15 09:59:59
 */
@Service("addressBookService")
public class AddressBookServiceImpl implements AddressBookService {
    @Resource
    private AddressBookMapper addressBookMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AddressBook queryById(Long id) {
        return this.addressBookMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param addressBook 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<AddressBook> queryByPage(AddressBook addressBook, PageRequest pageRequest) {
        long total = this.addressBookMapper.count(addressBook);
        return new PageImpl<>(this.addressBookMapper.queryAllByLimit(addressBook, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param addressBook 实例对象
     * @return 实例对象
     */
    @Override
    public AddressBook insert(AddressBook addressBook) {
        this.addressBookMapper.insert(addressBook);
        return addressBook;
    }

    /**
     * 修改数据
     *
     * @param addressBook 实例对象
     * @return 实例对象
     */
    @Override
    public AddressBook update(AddressBook addressBook) {
        this.addressBookMapper.update(addressBook);
        return this.queryById(addressBook.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.addressBookMapper.deleteById(id) > 0;
    }
}
