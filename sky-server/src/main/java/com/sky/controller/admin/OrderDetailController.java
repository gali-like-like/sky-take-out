package com.sky.controller.admin;

import com.sky.entity.OrderDetail;
import com.sky.service.OrderDetailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单明细表(OrderDetail)表控制层
 *
 * @author makejava
 * @since 2024-09-15 10:00:02
 */
@RestController
@RequestMapping("orderDetail")
public class OrderDetailController {
    /**
     * 服务对象
     */
    @Resource
    private OrderDetailService orderDetailService;

    /**
     * 分页查询
     *
     * @param orderDetail 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<OrderDetail>> queryByPage(OrderDetail orderDetail, PageRequest pageRequest) {
        return ResponseEntity.ok(this.orderDetailService.queryByPage(orderDetail, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<OrderDetail> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.orderDetailService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param orderDetail 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<OrderDetail> add(OrderDetail orderDetail) {
        return ResponseEntity.ok(this.orderDetailService.insert(orderDetail));
    }

    /**
     * 编辑数据
     *
     * @param orderDetail 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<OrderDetail> edit(OrderDetail orderDetail) {
        return ResponseEntity.ok(this.orderDetailService.update(orderDetail));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.orderDetailService.deleteById(id));
    }

}

