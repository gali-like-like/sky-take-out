package com.sky.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrdersSubmitDTO implements Serializable {

    //地址簿id
    @NotNull(message = "地址编号不能为空")
    private Long addressBookId;
    //付款方式
    @NotNull(message = "支付方式不能为空")
    private int payMethod;
    //备注
    @NotNull(message = "备注不能为空")
    private String remark;
    //预计送达时间
    @NotNull(message = "预计到达时间不能为空")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime estimatedDeliveryTime;

    @NotNull(message = "配送状态不能为空")
    @Max(value = 1,message = "配送状态只能为1或者0")
    @Min(value = 0,message = "配送状态只能为1或者0")
    //配送状态  1立即送出  0选择具体时间
    private Integer deliveryStatus;
    //餐具数量
    @NotNull(message = "餐具数据不能为空")
    private Integer tablewareNumber;
    //餐具数量状态  1按餐量提供  0选择具体数量
    @NotNull(message = "餐具数量状态不能为空")
    @Max(value = 1,message = "餐具数量状态只能为1或者0")
    @Min(value = 0,message = "餐具数量状态只能为1或者0")
    private Integer tablewareStatus;
    //打包费
    @NotNull(message = "不能没有打包费")
    @Min(value = 1,message = "打包费必须>=1")
    private Integer packAmount;
    //总金额
    @NotNull(message = "总金额不能为空")
    @Min(value = 1,message = "总金额必须>=1")
    private BigDecimal amount;
}
