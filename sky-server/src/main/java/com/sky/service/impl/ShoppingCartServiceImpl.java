package com.sky.service.impl;

import com.sky.entity.ShoppingCart;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 购物车(ShoppingCart)表服务实现类
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:01
 */
@Service("shoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Resource
    private ShoppingCartMapper shoppingCartMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ShoppingCart queryById(Long id) {
        return shoppingCartMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param shoppingCart 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    @Override
    public Page<ShoppingCart> queryByPage(ShoppingCart shoppingCart, PageRequest pageRequest) {
        long total = shoppingCartMapper.count(shoppingCart);
        return new PageImpl<>(shoppingCartMapper.queryAllByLimit(shoppingCart, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param shoppingCart 实例对象
     * @return 实例对象
     */
    @Override
    public ShoppingCart insert(ShoppingCart shoppingCart) {
        shoppingCartMapper.insert(shoppingCart);
        return shoppingCart;
    }

    /**
     * 修改数据
     *
     * @param shoppingCart 实例对象
     * @return 实例对象
     */
    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        shoppingCartMapper.update(shoppingCart);
        return queryById(shoppingCart.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return shoppingCartMapper.deleteById(id) > 0;
    }
}
