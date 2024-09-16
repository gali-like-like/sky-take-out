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
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.PasswordErrorException;
import com.sky.mapper.EmployeeMapper;
import com.sky.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Objects;

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
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        logger.info(employee.toString());
        //密码比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }


	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean editPassword(EmployPasswordDTO employPasswordDTO) throws AccountNotFoundException {
		/**
		 * 账号不存在就报账号不存在异常,存在则更改密码
		 * */
		Long id = employPasswordDTO.getEmpId();
		if(isExistsEmployeeById(id)) {
			employeeMapper.editPassword(employPasswordDTO);
			return true;
		}
		else
			return false;
	}
//

	@Override
	@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
	public Boolean changeStatus(Integer status, Long id) throws AccountNotFoundException {
		/**
		 * 账号不存在就报账号不存在异常,存在则更改状态
		 * */
		if(isExistsEmployeeById(id)) {
			employeeMapper.changeStatus(status, id);
			return true;
		}
		else
			return false;
	}

	@Override
	public PageInfo<Employee> pageDataByPageSize(EmployeePageQueryDTO queryDTO) {
		List<Employee> employeeList = employeeMapper.getAllEmployee(queryDTO.getName());
		PageHelper.startPage(queryDTO.getPage(),queryDTO.getPageSize());
		PageInfo<Employee> pageInfo = new PageInfo<>(employeeList);
		return pageInfo;
	}

	@Override
	public void addEmployee(EmployeeDTO employeeDTO) {
        employeeMapper.addEmployee(employeeDTO);
    }
	//用户不为空就存在，否则不存在
	@Override
	public Employee getEmployeeById(Long id) {
		return employeeMapper.getEmployeeById(id);
	}

	//账号不存在就报异常，存在就更新
	@Override
	@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
	public Boolean updateEmployee(EmployeeDTO employee) throws AccountNotFoundException {
		Long id = employee.getId();
		if(isExistsEmployeeById(id)) {
			employeeMapper.updateEmployee(employee);
			return true;
		}
		return false;
	}

	private Boolean isExistsEmployeeById(Long id) {
		if(id<0 || Objects.isNull(id))
			throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
		else {
			//判断id是否存在,存在就修改密码,不存在就返回false;
			Long employeeId = employeeMapper.existsId(id);
			if(Objects.isNull(employeeId))
				throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
			else
				return true;
		}
	}
}
