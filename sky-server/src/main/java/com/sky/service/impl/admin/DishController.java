package com.sky.service.impl.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;

/**
 * 菜品(Dish)表控制层
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:03
 */
@RestController
@RequestMapping("dish")
@Api(tags = "菜品相关接口")
@RequiredArgsConstructor
public class DishController {

    // 注入service
    public final DishService dishService;

    /**
     * 分页查询
     *
     * @param dishPageQueryDTO  筛选条件
     * @return 查询结果
     */
    @PostMapping("page")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public Result<HashMap<String, Object>> queryByPage(@Valid @RequestBody DishPageQueryDTO dishPageQueryDTO) {
        HashMap<String, Object> queryByPage = dishService.queryByPage(dishPageQueryDTO);
        return Result.success(queryByPage);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @ApiOperation(value = "通过主键查询单条数据", notes = "通过主键查询单条数据")
    public Result<DishVO> queryById(@PathVariable("id") Long id) {
        DishVO dishVO = dishService.queryById(id);
        return Result.success(dishVO);
    }

    /**
     * 新增数据
     *
     * @param dish 实体
     * @return 新增结果
     */
    @PostMapping
    @ApiOperation(value = "新增数据", notes = "新增数据")
    public Result<?> add(@Valid @RequestBody Dish dish) {
        int insert = dishService.insert(dish);
        if (insert > 0) {
            return Result.success("新增成功");
        }
        return Result.error("新增失败");
    }

    /**
     * 修改菜品
     *
     * @param dishDTO 实体
     * @return 编辑结果
     */
    @PutMapping
    @ApiOperation(value = "修改菜品", notes = "修改菜品")
    public Result<?> edit(@Valid @RequestBody DishDTO dishDTO) {
        int update = dishService.update(dishDTO);
        if (update > 0) {
            return Result.success("修改成功");
        }
        return Result.error("修改失败");
    }

    /**
     * 批量删除菜品
     *
     * @param ids id列表
     * @return 删除是否成功
     */
    @DeleteMapping
    @ApiOperation(value = "批量删除菜品", notes = "批量删除菜品")
    public Result<?> deleteById(@NotEmpty @Valid @RequestParam String ids) {
        Boolean b = dishService.deleteByIds(ids);
        return Result.success(b);
    }

    /**
     * 通过分类id查询菜品
     *
     * @param categoryId 主键
     * @return 单条数据
     */
    @GetMapping("list")
    @ApiOperation(value = "通过分类id查询菜品", notes = "通过分类id查询菜品")
    public Result<List<DishVO>> queryByCategoryId(@RequestParam("id") Long categoryId) {
        List<DishVO> dishVOS = dishService.queryByCategoryId(categoryId);
        return Result.success(dishVOS);
    }

    /**
     * 启/停售
     *
     * @param id 主键
     * @param status 状态
     * @return 编辑结果
     */
    @GetMapping("status/{status}")
    @ApiOperation(value = "启/停售", notes = "启/停售")
    public Result<?> updateStatus(@PathVariable("status") Integer status, @NotEmpty @Valid() @RequestParam Long id) {
        int b = dishService.updateStatus(id, status);
        if (b > 0) {
            return Result.success("修改成功");
        }
        return Result.error("修改失败");
    }
}

