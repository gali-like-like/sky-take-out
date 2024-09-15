package com.sky.controller.admin;

import com.sky.entity.SetmealDish;
import com.sky.service.SetmealDishService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 套餐菜品关系(SetmealDish)表控制层
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:02
 */
@RestController
@RequestMapping("setmealDish")
public class SetmealDishController {
    /**
     * 服务对象
     */
    @Resource
    private SetmealDishService setmealDishService;

    /**
     * 分页查询
     *
     * @param setmealDish 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<SetmealDish>> queryByPage(SetmealDish setmealDish, PageRequest pageRequest) {
        return ResponseEntity.ok(setmealDishService.queryByPage(setmealDish, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<SetmealDish> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(setmealDishService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param setmealDish 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<SetmealDish> add(SetmealDish setmealDish) {
        return ResponseEntity.ok(setmealDishService.insert(setmealDish));
    }

    /**
     * 编辑数据
     *
     * @param setmealDish 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<SetmealDish> edit(SetmealDish setmealDish) {
        return ResponseEntity.ok(setmealDishService.update(setmealDish));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(setmealDishService.deleteById(id));
    }

}

