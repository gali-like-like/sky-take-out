package com.sky.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String name;

    private String password;

    private String phone;

    private String sex;

    private String idNumber;

    private Integer status;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private Long createUser;

    private Long updateUser;

public Employee() {}

    public Employee(Long id,String username,String name,String password,String phone,String sex,String idNumber,Integer status) {
    	this.id = id;
    	this.username = username;
    	this.name = name;
    	this.password = password;
    	this.phone = phone;
    	this.sex = sex;
    	this.idNumber = idNumber;
    	this.status = status;
    }

    public void setId(Long id) {
    	this.id = id;
    }

    public Long getId() {
    	return this.id;
    }

    public void setUsername(String username) {
    	this.username = username;
    }

    public String getUsername() {
    	return this.username;
    }

    public void setPassword(String password) {
    	this.password = password;
    }

    public String getPassword() {
    	return this.password;
    }

    public void setPhone(String phone) {
    	this.phone = phone;
    }

    public String getPhone() {
    	return this.phone;
    }

    public void setSex(String sex) {
    	this.sex = sex;
    }

    public String getSex() {
    	return this.sex;
    }

    public void setIdNumber(String idN) {
    	this.idNumber = idN;
    }

    public String getIdNumber() {
    	return this.idNumber;
    }

    public void setStatus(Integer status) {
    	this.status = status;
    }

    public Integer getStatus() {
    	return this.status;
    }

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
