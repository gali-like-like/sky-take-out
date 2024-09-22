package com.sky.controller.user;

import com.sky.constant.MessageConstant;
import com.sky.dto.ShoppingCartDTO;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
@Api(tags={"购物车接口"})
@RestController("/user/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService cartService;

    @ApiOperation(value = "根据git 商品id和套餐id删除某人购物车中的商品")
    @DeleteMapping("/sub")
    public Result removeGoods(@ApiParam(required = true) @RequestBody ShoppingCartDTO shoppingCartDTO) {
        String result = cartService.removeGoods(shoppingCartDTO);
        return result.equals(MessageConstant.REMOVE_GODDS_SUCCESS)?Result.success(result):Result.error(result);
    }

    @ApiOperation(value="查看某人购物车中的商品")
    @GetMapping("/list")
    public Result catGoodsInShoppingCart(@ApiParam(name="userId") @RequestParam Long userId) {
        HashMap<String, Object> result = cartService.catGoodsInShoppingCart(userId);
        Object data = result.get("data");
        return Objects.nonNull(data)?Result.success((String) result.get("msg"),data):Result.error((String) result.get("msg"));
    }

    @ApiOperation(value="添加商品到某人购物车中")
    @PostMapping("/add")
    public Result addGoodsInShoppingCart(@ApiParam(required = true) @RequestBody ShoppingCartDTO shoppingCartDTO) {
        HashMap<String, Object> result = cartService.addGoodsInShoppingCart(shoppingCartDTO);
        String hintMsg = (String) result.get("msg");
        return hintMsg.equals(MessageConstant.ADD_GOODS_IN_SHOPPING_CART)?Result.success(hintMsg):Result.error(hintMsg);
    }

    @ApiOperation(value="清空某人购物车的所有商品")
    @DeleteMapping("/clean")
    public Result cleanShoppingCart(@NotNull(message = "用户编号不能为空") Long userId) {
        String result = cartService.cleanShoppingCart(userId);
        return result.equals(MessageConstant.CLEAN_SHOPPING_CART)?Result.success(result):Result.error(result);
    }
}
