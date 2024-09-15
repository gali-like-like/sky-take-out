package com.sky.controller.admin;

import com.sky.entity.DishFlavor;
import com.sky.service.DishFlavorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 菜品口味关系表(DishFlavor)表控制层
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:01
 */
@RestController
@RequestMapping("dishFlavor")
public class DishFlavorController {
    /**
     * 服务对象
     */
    @Resource
    private DishFlavorService dishFlavorService;

    /**
     * 分页查询
     *
     * @param dishFlavor  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<DishFlavor>> queryByPage(DishFlavor dishFlavor, PageRequest pageRequest) {
        return ResponseEntity.ok(dishFlavorService.queryByPage(dishFlavor, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<DishFlavor> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(dishFlavorService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param dishFlavor 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<DishFlavor> add(DishFlavor dishFlavor) {
        return ResponseEntity.ok(dishFlavorService.insert(dishFlavor));
    }

    /**
     * 编辑数据
     *
     * @param dishFlavor 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<DishFlavor> edit(DishFlavor dishFlavor) {
        return ResponseEntity.ok(dishFlavorService.update(dishFlavor));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(dishFlavorService.deleteById(id));
    }

}

