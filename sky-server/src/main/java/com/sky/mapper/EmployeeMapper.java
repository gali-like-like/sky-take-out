package com.sky.mapper;

import com.sky.dto.EmployeePageQueryDTO;
import com.sky.result.PageResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sky.dto.EmployPasswordDTO;
import com.sky.entity.Employee;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);
    
    @Update("update employee set password = md5(#{newPassword}) where id = #{empId}")
    void editPassword(EmployPasswordDTO passwordDTO);

    @Select("select id from employee where id = #{id}")
    Integer existsId(Integer id);
    
    @Update("update employee set status = #{status} where id = #{id}")
    public void changeStatus(Integer status,Integer id);
    //getAllEmployee
    public List<Employee> getAllEmployee(String name);
}


