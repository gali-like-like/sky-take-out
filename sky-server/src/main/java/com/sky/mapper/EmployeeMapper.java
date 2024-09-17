package com.sky.mapper;

import com.sky.dto.EmployPasswordDTO;
import com.sky.dto.EmployeeDTO;
import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     *
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    @Update("update employee set password = md5(#{newPassword}) where id = #{empId}")
    void editPassword(EmployPasswordDTO passwordDTO);

    @Select("select id from employee where id = #{id}")
    Long existsId(Long id);

    @Update("update employee set status = #{status} where id = #{id}")
    public void changeStatus(Integer status, Long id);

    //getAllEmployee
    public List<Employee> getAllEmployee(String name);

    //新增员工
    public void addEmployee(EmployeeDTO employeeDTO);

    //根据id查询员工
    public Employee getEmployeeById(Long id);

    //编辑员工信息
    public Boolean updateEmployee(EmployeeDTO employee);
}


