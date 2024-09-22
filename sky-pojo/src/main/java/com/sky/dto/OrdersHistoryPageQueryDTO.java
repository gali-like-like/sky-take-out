package com.sky.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Data
@NoArgsConstructor
//订单历史查询
public class OrdersHistoryPageQueryDTO {
    @NotNull(message = "用户编号不能为空")
    @Min(1)
    private Long userId;//用户编号

    @NotNull(message = "页码不能为空")
    @Min(1)
    private Integer page;//页码

    @NotNull(message = "分页大小不能为空")
    @Min(1)
    private Integer pageSize;//分页大小

    private Integer status;//订单状态
}
