package com.sky;

import com.sky.controller.admin.EmployeeController;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.result.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sky.dto.EmployPasswordDTO;
import com.sky.service.EmployeeService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@SpringBootTest
@ActiveProfiles("dev")
public class SkyApplicationTest {
	private Logger logger = LoggerFactory.getLogger(SkyApplication.class);
	@Autowired
	private EmployeeController employeeController;
	
	@Test
	@DisplayName("修改管理员密码是否成功")
	public void testEditPasswodSuccess() throws ExecutionException, InterruptedException, TimeoutException {
		EmployPasswordDTO employPasswordDTO = new  EmployPasswordDTO();
		employPasswordDTO.setEmpId(1);
		employPasswordDTO.setNewPassword("123456");
		employPasswordDTO.setOldPassword("456123");
		Result reult = employeeController.editPassword(employPasswordDTO);
		logger.info("修改密码成功：{}",reult.toString());
	}
	
	@Test
	@DisplayName("修改管理员状态成功")
	public void testChangeStatusSuccess() throws ExecutionException, InterruptedException, TimeoutException {
		Result result = employeeController.changeStatus(1, 1);
		logger.info("修改管理员状态成功：{}",result.toString());
	}
	
	@Test
	@DisplayName("修改管理员密码失败")
	public void testEditPasswodFail() throws ExecutionException, InterruptedException, TimeoutException {
		EmployPasswordDTO employPasswordDTO = new  EmployPasswordDTO();
		employPasswordDTO.setEmpId(2);
		employPasswordDTO.setNewPassword("456789");
		employPasswordDTO.setOldPassword("123456");
		Result result = employeeController.editPassword(employPasswordDTO);
		logger.info("修改密码成功：{}",result.toString());
	}
	
	@Test
	@DisplayName("修改管理员状态失败")
	public void testChangeStatusFail() throws ExecutionException, InterruptedException, TimeoutException {
		Result result = employeeController.changeStatus(1, 2);
		logger.info("修改管理员状态成功：{}",result.toString());
	}

	@Test
	@DisplayName("分页查询功能测试")
	public void testPageData() throws ExecutionException, InterruptedException, TimeoutException {
		int page = 1;
		int pageSize = 10;
		String name = "管理";
		EmployeePageQueryDTO queryDTO = new EmployeePageQueryDTO();
		queryDTO.setPage(page);
		queryDTO.setPageSize(pageSize);
		queryDTO.setName(name);
		Result result = employeeController.pageDataByPageNum(queryDTO);
		logger.info("结果:\n{}",result.toString());
	}

}
