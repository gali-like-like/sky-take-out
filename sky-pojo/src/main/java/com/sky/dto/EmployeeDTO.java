package com.sky.dto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
@Data

public class EmployeeDTO implements Serializable {
    @NotNull(message = "员工编号不能为空",groups = {GroupUpdate.class})
    private Long id;
    @NotNull(message = "员工账号不能为空",groups = {GroupAdd.class,GroupUpdate.class})
    private String username;
    @NotNull(message = "员工名字不能为空",groups = {GroupAdd.class,GroupUpdate.class})
    private String name;
    @NotNull(message = "员工手机号不能为空",groups = {GroupAdd.class,GroupUpdate.class})
    private String phone;
    @NotNull(message = "员工性别不能为空",groups = {GroupAdd.class,GroupUpdate.class})
    private String sex;
    @NotNull(message = "员工身份证不能为空",groups = {GroupAdd.class,GroupUpdate.class})
    private String idNumber;
    //添加接口对应组
    public interface GroupAdd {};
    //编辑接口对应组
    public interface GroupUpdate {};
}
