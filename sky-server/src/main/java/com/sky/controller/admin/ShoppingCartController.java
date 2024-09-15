package com.sky.controller.admin;

import com.sky.entity.ShoppingCart;
import com.sky.service.ShoppingCartService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 购物车(ShoppingCart)表控制层
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:00
 */
@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController {
    /**
     * 服务对象
     */
    @Resource
    private ShoppingCartService shoppingCartService;

    /**
     * 分页查询
     *
     * @param shoppingCart 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<ShoppingCart>> queryByPage(ShoppingCart shoppingCart, PageRequest pageRequest) {
        return ResponseEntity.ok(shoppingCartService.queryByPage(shoppingCart, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ShoppingCart> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(shoppingCartService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param shoppingCart 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<ShoppingCart> add(ShoppingCart shoppingCart) {
        return ResponseEntity.ok(shoppingCartService.insert(shoppingCart));
    }

    /**
     * 编辑数据
     *
     * @param shoppingCart 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<ShoppingCart> edit(ShoppingCart shoppingCart) {
        return ResponseEntity.ok(shoppingCartService.update(shoppingCart));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(shoppingCartService.deleteById(id));
    }

}

