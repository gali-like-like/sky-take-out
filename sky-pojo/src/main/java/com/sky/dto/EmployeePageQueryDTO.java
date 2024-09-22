package com.sky.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class EmployeePageQueryDTO implements Serializable {

    //员工姓名

    private String name;

    //页码
    @NotBlank(message = "页码不能为空")
    @Min(1)
    private int page;

    //每页显示记录数
    @Min(1)
//    @NotBlank(message = "分页大小不能为空")
    private int pageSize;

}
