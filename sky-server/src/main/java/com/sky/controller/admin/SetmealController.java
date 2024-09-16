package com.sky.controller.admin;

import com.github.pagehelper.PageInfo;
import com.sky.constant.MessageConstant;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
 * 套餐(Setmeal)表控制层
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:02
 */
@RestController
@RequestMapping("/admin/setmeal")
@Api(tags = "套餐接口")
@Slf4j
public class SetmealController {

    @Resource(name="setmealService")
    private SetmealService service;

    @ApiOperation(value="修改套餐")
    @PutMapping("/")
    public Result updateSetmeal(@ApiParam(required = true) @RequestBody SetmealDTO setmealDTO) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
            return service.updateSetmeal(setmealDTO);
        }).handle((res,e)->{
            return (Boolean)makeUpHandle(res,e);
        });
        return makeUpBoolFuture(future);
    }

    @ApiOperation(value="分页查询")
    @GetMapping("/page")
    public Result pageSetmeal(@ApiParam(required = true) SetmealPageQueryDTO setmealPageQueryDTO) {
        PageInfo<Setmeal> pageInfo = service.pageSetmeal(setmealPageQueryDTO);
        return Result.success(pageInfo);
    }

    @ApiOperation(value="修改套餐状态",notes="修改套餐是否可卖")
    @PostMapping("/status/{status}")
    public Result updateSetmealStatus(@ApiParam(name="status",value="1",required = true) @PathVariable("status") Integer status,@ApiParam(name="id",required = true) Long id) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
            return service.updateSetmealStatus(status,id);
        }).handle((res,e)->{
            return (Boolean)makeUpHandle(res,e);
        });
        return makeUpBoolFuture(future);
    }

    @ApiOperation(value="批量删除套餐")
    @DeleteMapping("/")
    public Result deleteSetmals(@ApiParam(name="ids",required = true) @RequestParam List<Long> ids) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(()->service.deleteSetmals(ids)).handle((res,e)->{
            return (Boolean)makeUpHandle(res,e);
        });
        return makeUpBoolFuture(future);
    }

    @ApiOperation(value="添加套餐")
    @PostMapping("/")
    public Result addSetmeal(@ApiParam(required = true) @RequestBody SetmealDTO setmealDTO) {
        Long result = service.addSetmeal(setmealDTO);
        return Objects.isNull(result)?Result.error(MessageConstant.UNKNOWN_ERROR):Result.success(result);
    }

    @ApiOperation(value="根据id查询")
    @GetMapping("/{id}")
    public Result getSetmealById(@ApiParam(name="id",required = true) @PathVariable("id") Long id) {
        Setmeal setmeal = service.getSetmealById(id);
        if(Objects.nonNull(setmeal))
            return Result.success(setmeal);
        return Result.error(MessageConstant.ACCOUNT_NOT_FOUND);
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

