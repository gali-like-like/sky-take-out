package com.sky.controller.admin;

import com.sky.entity.Employee;
import com.sky.mapper.EmployeeMapper;
import com.sky.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    // http://localhost:8080/test/hello
    @GetMapping("hello")
    public String hello() {
        return "项目已启动，使用愉快！";
    }

    @Autowired
    EmployeeMapper employeeMapper;

    // http://localhost:8081/test/testData
    @GetMapping("testData")
    public Result<Employee> getEmployee() {
        Employee admin = employeeMapper.getByUsername("admin");
        return Result.success(admin);
    }
}
