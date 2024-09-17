package com.sky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.sky.controller.admin.EmployeeController;
import com.sky.dto.EmployeeDTO;
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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@SpringBootTest
@ActiveProfiles("dev")
public class SkyApplicationTest {
	private Logger logger = LoggerFactory.getLogger(SkyApplication.class);
	@Autowired
	private EmployeeController employeeController;
	
	@Test
	@DisplayName("修改管理员密码成功")
	public void testEditPasswodSuccess() throws ExecutionException, InterruptedException, TimeoutException {
		EmployPasswordDTO employPasswordDTO = new  EmployPasswordDTO();
		employPasswordDTO.setEmpId(1L);
		employPasswordDTO.setNewPassword("123456");
		employPasswordDTO.setOldPassword("456123");
		Result result = employeeController.editPassword(employPasswordDTO);
		assertEquals(1,result.getCode());
	}
	
	@Test
	@DisplayName("修改管理员状态成功")
	public void testChangeStatusSuccess() throws ExecutionException, InterruptedException, TimeoutException {
		Result result = employeeController.changeStatus(1, 1L);
		assertEquals(1,result.getCode());
	}
	
	@Test
	@DisplayName("修改管理员密码失败")
	public void testEditPasswodFail() throws ExecutionException, InterruptedException, TimeoutException {
		EmployPasswordDTO employPasswordDTO = new  EmployPasswordDTO();
		employPasswordDTO.setEmpId(2L);
		employPasswordDTO.setNewPassword("456789");
		employPasswordDTO.setOldPassword("123456");
		Result result = employeeController.editPassword(employPasswordDTO);
		assertEquals(0,result.getCode());
	}
	
	@Test
	@DisplayName("修改管理员状态失败")
	public void testChangeStatusFail() throws ExecutionException, InterruptedException, TimeoutException {
		Result result = employeeController.changeStatus(1, 2L);
		assertEquals(0,result.getCode());
	}

	@Test
	@DisplayName("分页查询功能成功")
	public void testPageData() throws ExecutionException, InterruptedException, TimeoutException {
		int page = 1;
		int pageSize = 10;
		String name = "管理";
		EmployeePageQueryDTO queryDTO = new EmployeePageQueryDTO();
		queryDTO.setPage(page);
		queryDTO.setPageSize(pageSize);
		queryDTO.setName(name);
		Result result = employeeController.pageDataByPageNum(queryDTO);
		assertEquals(1,result.getCode());
	}

	@Test
	@DisplayName(value="新增员工成功")
	//除了id可以为空外，其他都不能为空
	// 这块不能重复执行，因为username不能重复
	public void testAddEmployeeSuccess() throws ExecutionException, InterruptedException, TimeoutException {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setSex("女");
		employeeDTO.setPhone("7894555");
		employeeDTO.setIdNumber("7777777777");
		employeeDTO.setName("貂蝉");
		employeeDTO.setUsername("daqiao");

		Result result = employeeController.employee(employeeDTO);
		assertEquals(1,result.getCode());
	}

	@Test
	@DisplayName(value="根据id查询用户成功")
	public void testQueryEmployeeSuccess() throws ExecutionException, InterruptedException, TimeoutException {
		Result result = employeeController.getEmployeeById(1L);
		assertEquals(1,result.getCode());
	}

	@Test
	@DisplayName(value="根据id查询用户失败")
	public void testQueryEmployeeFail() throws ExecutionException, InterruptedException, TimeoutException {
		Result result = employeeController.getEmployeeById(2L);
		assertEquals(0,result.getCode());
	}

	@Test
	@DisplayName(value="修改员工信息成功")
	public void testUpdateEmployeeSuccess() throws ExecutionException, InterruptedException, TimeoutException {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setId(1L);
		employeeDTO.setName("管理大佬");
		Result result = employeeController.editEmployee(employeeDTO);
		assertEquals(1,result.getCode());
	}

	@Test
	@DisplayName(value="修改员工信息失败")
	public void testUpdateEmployeeFail() throws ExecutionException, InterruptedException, TimeoutException {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setId(2L);
		employeeDTO.setName("管理大佬");
		Result result = employeeController.editEmployee(employeeDTO);
		assertEquals(0,result.getCode());
	}
}
