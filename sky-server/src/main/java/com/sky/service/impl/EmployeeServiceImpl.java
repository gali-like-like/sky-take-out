package com.sky.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.EmployPasswordDTO;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.exception.*;
import com.sky.mapper.EmployeeMapper;
import com.sky.service.EmployeeService;
import org.apache.xmlbeans.impl.jam.mutable.MElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    private Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    public HashMap<String,Object> login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();
        //ConcurrentHashMap 不允许放入null值
        HashMap<String,Object> hashMap = new HashMap<>();//存储员工数据和提示信息的
        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            hashMap.put("data",null);
            hashMap.put("msg",MessageConstant.ACCOUNT_NOT_FOUND);
            return hashMap;
        }
        //密码比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())) {
            //密码错误
            hashMap.put("data",null);
            hashMap.put("msg",MessageConstant.PASSWORD_ERROR);
            return hashMap;
        }
        else {
            if (employee.getStatus() == StatusConstant.DISABLE) {
                //账号被锁定
                hashMap.put("data",employee);
                hashMap.put("msg",MessageConstant.ACCOUNT_LOCKED);
                return hashMap;
            }
            else {
                hashMap.put("data",employee);
                hashMap.put("msg",MessageConstant.LOGIN_SUCCESS);
                return hashMap;
            }
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean editPassword(EmployPasswordDTO employPasswordDTO) throws AccountNotFoundException,FieldNotNullException {
        /**
         * 账号不存在就报账号不存在异常,存在则更改密码
         * */
        Long id = employPasswordDTO.getEmpId();
        idIsNull(id);
        if (isExistsEmployeeById(id)) {
            employeeMapper.editPassword(employPasswordDTO);
            return true;
        } else
            return false;
    }
//

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Boolean changeStatus(Integer status, Long id) throws AccountNotFoundException {
        /**
         * 账号不存在就报账号不存在异常,存在则更改状态
         * */
        if (isExistsEmployeeById(id)) {
            employeeMapper.changeStatus(status, id);
            return true;
        } else
            return false;
    }

    @Override
    public PageInfo<Employee> pageDataByPageSize(EmployeePageQueryDTO queryDTO) {
        pageIsNull(queryDTO.getPage());
        pageSizeIsNull(queryDTO.getPageSize());
        PageHelper.startPage(queryDTO.getPage(), queryDTO.getPageSize());
        List<Employee> employeeList = employeeMapper.getAllEmployee(queryDTO.getName());
        PageInfo<Employee> pageInfo = new PageInfo<>(employeeList);
        return pageInfo;
    }

    @Override
    public void addEmployee(EmployeeDTO employeeDTO) {
        employeeDTOIsNull(employeeDTO);
        employeeMapper.addEmployee(employeeDTO);
    }

    //用户不为空就存在，否则不存在
    @Override
    public HashMap<String,Object> getEmployeeById(Long id) throws EmployeeIdNotNullException {
        idIsNull(id);
        HashMap<String,Object> data = new HashMap<>();
        Employee employee = employeeMapper.getEmployeeById(id);
        data.put("employ",employee);
        data.put("msg", MessageConstant.EMPLOYEE_QUERY_SUCCESS);
        return data;
    }

    //账号不存在就报异常，存在就更新
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public String updateEmployee(EmployeeDTO employee) throws AccountNotFoundException,FieldNotNullException{
        employeeMapper.updateEmployee(employee);
        return MessageConstant.EMPLOYEE_UPDATE_SUCCESS;
    }

    private void employeeDTOIsNull(EmployeeDTO employee) throws FieldNotNullException,AccountNotFoundException {
        String name = employee.getName();
        String phone = employee.getPhone();
        String sex = employee.getSex();
        String idNumber = employee.getIdNumber();
        String username = employee.getUsername();
        nameIsNull(name);
        phoneIsNull(phone);
        sexIsNull(sex);
        usernameIsNull(username);
        idNumberIsNull(idNumber);
    }

    private void idIsNull(Long id) {
        if(Objects.isNull(id))
            throw new FieldNotNullException();
    }

    private Boolean isExistsEmployeeById(Long id) {
        //判断id是否存在,存在就修改密码,不存在就返回false;
        Long employeeId = employeeMapper.existsId(id);
        if (Objects.isNull(employeeId))
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        else
            return true;
    }

    private void nameIsNull(String name) {
        if(Objects.isNull(name))
            throw new EmployeeNameNotNullException();
    }

    private void phoneIsNull(String phone) {
        if(Objects.isNull(phone))
            throw new EmployeePhoneNotNullException();
    }

    private void sexIsNull(String sex) {
        if(Objects.isNull(sex))
            throw new EmployeeSexNotNullException();
    }

    private void idNumberIsNull(String idNumber) {
        if(Objects.isNull(idNumber))
            throw new EmployeeIDNumberNotNullException();
    }

    private void usernameIsNull(String username) {
        if(Objects.isNull(username))
            throw new EmployeeUsernameNotNullException();
    }

    private void pageIsNull(Integer page) {
        if(Objects.isNull(page))
            throw new FieldNotNullException(MessageConstant.PAGE_NOT_NULL);
    }

    private void pageSizeIsNull(Integer pageSize) {
        if(Objects.isNull(pageSize)) {
            throw new FieldNotNullException(MessageConstant.PAGE_SIZE_NOT_NULL);
        }
    }
}
