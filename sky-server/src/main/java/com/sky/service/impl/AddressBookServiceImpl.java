package com.sky.service.impl;

import com.sky.entity.AddressBook;
import com.sky.mapper.AddressBookMapper;
import com.sky.service.AddressBookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 地址簿(AddressBook)表服务实现类
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:03
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
        return addressBookMapper.queryById(id);
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
        long total = addressBookMapper.count(addressBook);
        return new PageImpl<>(addressBookMapper.queryAllByLimit(addressBook, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param addressBook 实例对象
     * @return 实例对象
     */
    @Override
    public AddressBook insert(AddressBook addressBook) {
        addressBookMapper.insert(addressBook);
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
        addressBookMapper.update(addressBook);
        return queryById(addressBook.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return addressBookMapper.deleteById(id) > 0;
    }
}
