package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.dto.ShoppingCartDishDTO;
import com.sky.dto.ShoppingCartSetmealDTO;
import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.HashMap;
import java.util.List;

/**
 * 购物车(ShoppingCart)表服务接口
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:00
 */
public interface ShoppingCartService {

    //删除购物车中一个商品
    public String removeGoods(ShoppingCartDTO shoppingCartDTO);
    //查看某人购物车中的所有菜品
    public HashMap<String,Object> catDishsInShoppingCart(Long userId);
    //查看某人购物车中的所有套餐
    public HashMap<String,Object> catSetmealsInShoppingCart(Long userId);
    //添加商品到购物车
    public HashMap<String,Object> addGoodsInShoppingCart(ShoppingCartDTO shoppingCartDTO);
    //根据套餐id查看所有套餐内的菜品
    public HashMap<String,Object> getDishBySetmealId(Long setmealId);
    //查看个人购物车里的所有商品
    public HashMap<String,Object> catGoodsInShoppingCart(Long userId);
    //清空购物车
    public String cleanShoppingCart(Long userId);
    //修改购物车中同个商品数量
    public String updateGoodsCount(ShoppingCartDTO shoppingCartDTO);

}
