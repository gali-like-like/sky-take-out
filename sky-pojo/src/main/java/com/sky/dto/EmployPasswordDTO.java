package com.sky.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@NoArgsConstructor
@Data

public class EmployPasswordDTO {

    private Long empId;
    private String newPassword;
    @NotNull(message = "旧密码不能为空",groups = {inter1.class,inter2.class})
    @Min(value = 10,groups = {inter1.class})
    private String oldPassword;
    public interface inter1 {};
    interface inter2 {};
}

