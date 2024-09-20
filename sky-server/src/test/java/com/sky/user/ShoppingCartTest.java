package com.sky.user;

import com.sky.constant.MessageConstant;
import com.sky.controller.user.ShoppingCartController;
import com.sky.dto.ShoppingCartDTO;
import com.sky.result.Result;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShoppingCartTest {

    @Autowired
    private ShoppingCartController cartController;


    //清空购物车
    @Test
    @DisplayName("成功清空购物车")
    @Order(1)
    public void testCleanCartSuccess() {
        Result result = cartController.cleanShoppingCart(1L);
        assertEquals(MessageConstant.CLEAN_SHOPPING_CART,result.getMsg());
    }

    //清空购物车
    @Test
    @DisplayName("清空购物车失败")
    @Order(1)
    public void testCleanCartFail() {
        Result result = cartController.cleanShoppingCart(1L);
        assertEquals(MessageConstant.CLEAN_SHOPPING_CART,result.getMsg());
    }

    //添加商品到购物车
    @Test
    @DisplayName("成功添加商品到购物车")
    @Order(2)
    public void testAddGoods() {
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setUserId(1L);
        shoppingCartDTO.setNumber(2);
        shoppingCartDTO.setDishId(1L);
        Result result = cartController.addGoodsInShoppingCart(shoppingCartDTO);
        assertEquals(MessageConstant.ADD_GOODS_IN_SHOPPING_CART,result.getMsg());
    }
    //查看商品到购物车
    @Test
    @DisplayName("成功查看个人购物车的所有商品")
    @Order(3)
    public void testGetGoods() {
        Result result = cartController.catGoodsInShoppingCart(1L);
        assertEquals(MessageConstant.USER_QUERY_ALL_GOODS_IN_SHOPPING_CART,result.getMsg());
    }
    //删除购物车中的商品
    @Test
    @DisplayName("成功删除购物车中的商品")
    @Order(3)
    public void testRemoveGoods() {
        ShoppingCartDTO cartDTO = new ShoppingCartDTO();
        cartDTO.setUserId(1L);
        cartDTO.setDishId(2L);
        cartDTO.setNumber(3);
        cartDTO.setSetmealId(null);
        Result result = cartController.removeGoods(cartDTO);
        assertEquals(MessageConstant.REMOVE_GODDS_SUCCESS,result.getMsg());
    }

}
