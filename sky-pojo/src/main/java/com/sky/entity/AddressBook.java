package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * 地址簿
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空")
    private Long id;

    //用户id
    private Long userId;

    //收货人
    private String consignee;

    //手机号
    @NotBlank(message = "手机号不能为空")
    private String phone;

    //性别 0 女 1 男
    @NotBlank(message = "性别不能为空")
    private String sex;

    //省级区划编号
    private String provinceCode;

    //省级名称
    private String provinceName;

    //市级区划编号
    private String cityCode;

    //市级名称
    private String cityName;

    //区级区划编号
    private String districtCode;

    //区级名称
    private String districtName;

    //详细地址
    @NotBlank(message = "详细地址不能为空")
    private String detail;

    //标签
    private String label;

    //是否默认 0否 1是
    @Min(value = 0, message = "是否默认只能为0或1")
    @Max(value = 1, message = "是否默认只能为0或1")
    private Integer isDefault;
}
