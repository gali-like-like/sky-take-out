package com.sky.mapper;

import com.sky.dto.DishDTO;
import com.sky.dto.ShoppingCartDTO;
import com.sky.dto.ShoppingCartDishDTO;
import com.sky.dto.ShoppingCartSetmealDTO;
import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.*;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * 购物车(ShoppingCart)表数据库访问层
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:52:47
 */
@Mapper
public interface ShoppingCartMapper {

   //删除购物车中一个商品
   public void removeGoods(ShoppingCartDTO shoppingCartDTO);
   //修改购物车中同个商品数量
   public void updateGoodsCount(ShoppingCartDTO shoppingCartDTO);
   //查看购物车中的菜品
   public List<ShoppingCartDishDTO> catDishsInShoppingCart();
   //查看购物车中的套餐
   public List<ShoppingCartSetmealDTO> catSetmealsInShoppingCart();
   //添加商品到购物车
   public Integer addGoodsInShoppingCart();
   //根据套餐id查看所有套餐内的菜品
   public List<ShoppingCartDishDTO> getDishBySetmealId(Long setmealId);
   //清空购物车
   public void cleanShoppingCart(Long userId);

}

