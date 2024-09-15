package com.sky.service.impl;

import com.sky.entity.ShoppingCart;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 购物车(ShoppingCart)表服务实现类
 *
 * @author makejava
 * @since 2024-09-15 10:00:00
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
        return this.shoppingCartMapper.queryById(id);
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
        long total = this.shoppingCartMapper.count(shoppingCart);
        return new PageImpl<>(this.shoppingCartMapper.queryAllByLimit(shoppingCart, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param shoppingCart 实例对象
     * @return 实例对象
     */
    @Override
    public ShoppingCart insert(ShoppingCart shoppingCart) {
        this.shoppingCartMapper.insert(shoppingCart);
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
        this.shoppingCartMapper.update(shoppingCart);
        return this.queryById(shoppingCart.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.shoppingCartMapper.deleteById(id) > 0;
    }
}
