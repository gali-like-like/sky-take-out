package com.sky.service.impl.admin;

import com.github.pagehelper.PageInfo;
import com.sky.constant.MessageConstant;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 菜品及套餐分类(Category)表控制层
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:04
 */
@RestController
@RequestMapping("/admin/category")
@Data
@Api(tags="分类接口")
@Slf4j
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/list")
    @ApiOperation(value="根据分类查询")
    public Result getCategoryByType(@ApiParam(name="type",required = true) @RequestParam Long type) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<List<Category>> future = CompletableFuture.supplyAsync(() -> {
            return categoryService.queryByType(type);
        }).handle((res,e) -> {
            return (List<Category>) makeUpHandle(res,e);
        });
        List<Category> categories = future.get(3, TimeUnit.SECONDS);
        if(Objects.isNull(categories))
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        return Result.success(categories);
    }

    @ApiOperation(value="添加分类")
    @PostMapping("/")
    public Result addCategory(@ApiParam(required = true) @RequestBody CategoryDTO category) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()->{
           return categoryService.addCategory(category);
        }).handle((res,e)->{
            return (Integer)makeUpHandle(res,e);
        });
        Integer id = future.get(3,TimeUnit.SECONDS);
        if(Objects.isNull(id))
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        return Result.success(id);
    }

    @ApiOperation(value="修改分类")
    @PutMapping("/")
    public Result updateCategory(@ApiParam(required = true) @RequestBody CategoryDTO categoryDTO) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
            return categoryService.updateCategory(categoryDTO);
        }).handle((res,e) -> {
            return (Boolean)makeUpHandle(res,e);
        });
        return makeUpBoolFuture(future);
    }

    @ApiOperation(value="分页查询",notes="通过类型或者名字，分页，返回分页数据")
    @GetMapping("/page")
    public Result queryPageCategory(@RequestParam CategoryPageQueryDTO pageQueryDTO) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<PageInfo<Category>> future = CompletableFuture.supplyAsync(() -> {
           return categoryService.queryPageCategory(pageQueryDTO);
        }).handle((res,e) -> {
            return (PageInfo<Category>)makeUpHandle(res,e);
        });
        PageInfo<Category> categoryPageInfo = future.get(3,TimeUnit.SECONDS);
        if(categoryPageInfo==null) {
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
        return Result.success(categoryPageInfo);
    }

    @ApiOperation(value="删除分类",notes="根据id删除分类")
    @DeleteMapping("/")
    public Result deleteCategoryById(@ApiParam(required = true,name="id") @RequestParam Long id) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
            return categoryService.deleteCategoryById(id);
        }).handle((res,e) -> {
            return (Boolean) makeUpHandle(res,e);
        });
        return makeUpBoolFuture(future);
    }

    @ApiOperation(value="修改状态",notes="根据id修改状态")
    @PostMapping("/status/{status}")
    public Result changeStatus(@ApiParam(name="id",required = true) @RequestParam Long id,
                               @ApiParam(name="status",required = true) @PathVariable Integer status) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
            return categoryService.changeStatus(id,status);
        }).handle((res,e) -> {
            return (Boolean)makeUpHandle(res,e);
        });
        return makeUpBoolFuture(future);
    }
    //布尔类型的future转Result
    private Result makeUpBoolFuture(CompletableFuture<Boolean> future) throws ExecutionException, InterruptedException, TimeoutException {
        Boolean res = future.get(3,TimeUnit.SECONDS);
        if(Objects.isNull(res))
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        else if(res)
            return Result.success();
        else
            return Result.error(MessageConstant.ACCOUNT_NOT_FOUND);
    }
    //异步处理异常
    private Object makeUpHandle(Object res,Throwable e) {
        if(Objects.isNull(e))
            return res;
        log.error("发生异常:\n{}",e.getLocalizedMessage());
        return null;
    }
}

