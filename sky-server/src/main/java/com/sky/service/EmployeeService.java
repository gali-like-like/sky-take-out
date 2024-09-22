package com.sky.service;

import com.github.pagehelper.PageInfo;
import com.sky.dto.EmployPasswordDTO;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.PasswordEditFailedException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public interface EmployeeService {

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    HashMap<String,Object> login(EmployeeLoginDTO employeeLoginDTO) throws AccountNotFoundException, AccountLockedException, PasswordEditFailedException;

    Boolean editPassword(EmployPasswordDTO employPasswordDTO);

    Boolean changeStatus(Integer status, Long id);

    PageInfo<Employee> pageDataByPageSize(EmployeePageQueryDTO queryDTO);

    //新增员工
    public void addEmployee(EmployeeDTO employeeDTO);

    //根据id查询员工
    public HashMap<String,Object> getEmployeeById(Long id);

    //编辑员工信息
    public String updateEmployee(EmployeeDTO employee);
}
