package com.sky.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "员工登录返回的数据格式")
public class EmployeeLoginVO implements Serializable {

    @ApiModelProperty("主键值")
    private Long id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("jwt令牌")
    private String token;

    public EmployeeLoginVO() {
    }

    private EmployeeLoginVO(Long id, String username, String name, String token) {
        this.id = id;
        this.userName = username;
        this.name = name;
        this.token = token;
    }

    public static EmployeeLoginVO builder(Long id, String username, String name, String token) {
        // TODO Auto-generated method stub
        EmployeeLoginVO employeeLoginVO = new EmployeeLoginVO(id, username, name, token);
        return employeeLoginVO;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
