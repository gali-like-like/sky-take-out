package com.sky.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.dto.EmployPasswordDTO;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);
    
    Boolean editPassword(EmployPasswordDTO employPasswordDTO);
    
    Boolean changeStatus(Integer status,Long id);

    PageInfo<Employee> pageDataByPageSize(EmployeePageQueryDTO queryDTO);
    //新增员工
    public void addEmployee(EmployeeDTO employeeDTO);
    //根据id查询员工
    public Employee getEmployeeById(Long id);
    //编辑员工信息
    public Boolean updateEmployee(EmployeeDTO employee);
}
