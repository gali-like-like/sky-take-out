package com.sky.controller.admin;

import com.sky.entity.Setmeal;
import com.sky.service.SetmealService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 套餐(Setmeal)表控制层
 *
 * @author makejava
 * @since 2024-09-15 09:59:57
 */
@RestController
@RequestMapping("setmeal")
public class SetmealController {
    /**
     * 服务对象
     */
    @Resource
    private SetmealService setmealService;

    /**
     * 分页查询
     *
     * @param setmeal     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Setmeal>> queryByPage(Setmeal setmeal, PageRequest pageRequest) {
        return ResponseEntity.ok(this.setmealService.queryByPage(setmeal, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Setmeal> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.setmealService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param setmeal 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Setmeal> add(Setmeal setmeal) {
        return ResponseEntity.ok(this.setmealService.insert(setmeal));
    }

    /**
     * 编辑数据
     *
     * @param setmeal 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Setmeal> edit(Setmeal setmeal) {
        return ResponseEntity.ok(this.setmealService.update(setmeal));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.setmealService.deleteById(id));
    }

}

