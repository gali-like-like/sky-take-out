package com.sky.dto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Data

public class EmployeeDTO implements Serializable {
    @NotNull(message = "员工编号不能为空")
    private Long id;
    @NotNull(message = "员工账号不能为空")
    private String username;
    @NotNull(message = "员工名字不能为空")
    private String name;
    @NotNull(message = "员工手机号不能为空")
    private String phone;
    @NotNull(message = "员工性别不能为空")
    private String sex;
    @NotNull(message = "员工身份证不能为空")
    private String idNumber;
}
