package com.sky.controller.user;

import com.sky.entity.AddressBook;
import com.sky.result.Result;
import com.sky.service.AddressBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 地址簿(AddressBook)表控制层
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-09-15 11:42:03
 */
@RestController
@RequestMapping("user/addressBook")
@Api(tags = "C端-地址簿接口")
public class AddressBookController {
    /**
     * 服务对象
     */
    @Resource
    private AddressBookService addressBookService;

    /**
     * 查询所有地址
     *
     * @return 查询结果
     */
    @ApiOperation(value = "查询当前登录用户的所有地址信息")
    @GetMapping("list")
    public Result<?> queryByPage(@RequestParam Long userId) {
        List<AddressBook> addressBooks = addressBookService.queryAll(userId);
        return Result.success(addressBooks);
    }

    /**
     * 通过主键查询地址
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "根据id查询地址信息")
    @GetMapping("{id}")
    public Result<?> queryById(@PathVariable("id") @NotNull Long id) {
        AddressBook addressBook = addressBookService.queryById(id);
        return Result.success(addressBook);
    }

    /**
     * 新增数据
     *
     * @param addressBook 实体
     * @return 新增结果
     */
    @ApiOperation(value = "新增地址")
    @PostMapping
    public Result<?> add(@RequestBody AddressBook addressBook) {
        int insert = addressBookService.insert(addressBook);
        String message = insert > 0 ? "新增成功" : "新增失败";
        return Result.success(message);
    }

    /**
     * 根据id修改地址
     *
     * @param addressBook 实体
     * @return 编辑结果
     */
    @ApiOperation(value = "编辑地址")
    @PutMapping
    public Result<?> edit(@RequestBody @Valid AddressBook addressBook) {
        int update = addressBookService.update(addressBook);
        String message = update > 0 ? "编辑成功" : "编辑失败";
        return Result.success(message);
    }

    /**
     * 根据id删除地址
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation(value = "删除地址")
    @DeleteMapping
    public Result<Boolean> deleteById(@RequestParam("id") Long id) {
        boolean b = addressBookService.deleteById(id);
        return Result.success(b);
    }

    /**
     * 根据用户id查询默认地址
     *
     * @return 默认地址
     */
    @ApiOperation(value = "查询默认地址")
    @GetMapping("default")
    public Result<AddressBook> queryDefaultAddress(@RequestParam("userId") Long userId) {
        AddressBook addressBook = addressBookService.queryDefaultAddress(userId);
        return Result.success(addressBook);
    }

    /**
     * 根据地址id修改默认地址
     *
     * @return 修改结果
     */
    @ApiOperation(value = "设置默认地址")
    @PutMapping("default")
    public Result<Boolean> setDefaultAddress(@RequestParam("addressId") Long addressId, @RequestParam("userId") Long userId) {
        boolean b = addressBookService.setDefaultAddress(addressId,userId);
        return Result.success(b);
    }



}

