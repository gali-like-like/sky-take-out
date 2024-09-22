package com.sky.service.impl;

import com.sky.convert.AddressBookConvert;
import com.sky.entity.AddressBook;
import com.sky.mapper.AddressBookMapper;
import com.sky.service.AddressBookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
    public int insert(AddressBook addressBook) {
        return addressBookMapper.insert(addressBook);
    }

    /**
     * 修改数据
     *
     * @param addressBook 实例对象
     * @return 实例对象
     */
    @Override
    public int update(AddressBook addressBook) {
        // 先查一遍，判断是否存在
        AddressBook entity = addressBookMapper.queryById(addressBook.getId());
        if (entity == null) {
            return 0;
        }
        // 复制属性
        AddressBookConvert.INSTANCE.copyProperties(addressBook, entity);
        return addressBookMapper.update(entity);
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

    @Override
    public List<AddressBook> queryAll(Long userId) {
        return addressBookMapper.queryAll(userId);
    }

    /**
     * 查询默认地址
     *
     * @return 默认地址
     */
    @Override
    public AddressBook queryDefaultAddress(Long userId) {
        return addressBookMapper.queryDefaultAddress(userId);
    }

    /**
     * 设置默认地址
     *
     * @return 是否成功
     */
    @Override
    @Transactional
    public boolean setDefaultAddress( Long addressId, Long userId) {
        // 先查默认地址
        AddressBook defaultAddress = addressBookMapper.queryDefaultAddress(userId);
        if (defaultAddress != null ) {
            addressBookMapper.cancelDefaultAddress(defaultAddress.getId());
        }
        // 设置默认地址
        return addressBookMapper.setDefaultAddress(addressId) > 0;
    }
}
