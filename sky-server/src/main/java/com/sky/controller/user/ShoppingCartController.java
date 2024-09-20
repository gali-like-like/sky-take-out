package com.sky.controller.user;

import com.sky.dto.ShoppingCartDTO;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
@Api(tags={"购物车接口"})
@RestController("/user/shoppingCart")
public class ShoppingCartController {


    @ApiOperation(value = "删除购物车中的商品")
    @DeleteMapping("/sub")
    public Result removeGoods(@ApiParam(required = true) @RequestBody ShoppingCartDTO shoppingCartDTO) {
        return Result.success();
    }

    @ApiOperation(value="查看购物车中的商品")
    @GetMapping("/list")
    public Result catGoodsInShoppingCart() {
        return Result.success();
    }

    @ApiOperation(value="添加商品进入购物车")
    @PostMapping("/add")
    public Result addGoodsInShoppingCart(@ApiParam(required = true) @RequestBody ShoppingCartDTO shoppingCartDTO) {
        return Result.success();
    }

    @ApiOperation(value="清空购物车")
    @DeleteMapping("/clean")
    public Result cleanShoppingCart() {
        return Result.success();
    }
}
