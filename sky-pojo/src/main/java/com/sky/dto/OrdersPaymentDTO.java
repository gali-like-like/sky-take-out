package com.sky.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class OrdersPaymentDTO implements Serializable {
    //订单号
    @NotNull(message = "订单号不能为空")
    private String orderNumber;

    //付款方式
    @NotNull(message = "支付方式不能为空")
    private Integer payMethod;

}
