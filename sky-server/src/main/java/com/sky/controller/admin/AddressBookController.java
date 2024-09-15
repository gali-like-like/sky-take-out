package com.sky.controller.admin;

import com.sky.entity.AddressBook;
import com.sky.service.AddressBookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 地址簿(AddressBook)表控制层
 *
 * @author makejava
 * @since 2024-09-15 09:59:59
 */
@RestController
@RequestMapping("addressBook")
public class AddressBookController {
    /**
     * 服务对象
     */
    @Resource
    private AddressBookService addressBookService;

    /**
     * 分页查询
     *
     * @param addressBook 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<AddressBook>> queryByPage(AddressBook addressBook, PageRequest pageRequest) {
        return ResponseEntity.ok(this.addressBookService.queryByPage(addressBook, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<AddressBook> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.addressBookService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param addressBook 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<AddressBook> add(AddressBook addressBook) {
        return ResponseEntity.ok(this.addressBookService.insert(addressBook));
    }

    /**
     * 编辑数据
     *
     * @param addressBook 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<AddressBook> edit(AddressBook addressBook) {
        return ResponseEntity.ok(this.addressBookService.update(addressBook));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.addressBookService.deleteById(id));
    }

}

