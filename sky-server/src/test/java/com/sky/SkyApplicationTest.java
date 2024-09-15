package com.sky;

import com.sky.dto.EmployPasswordDTO;
import com.sky.service.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
public class SkyApplicationTest {
    private Logger logger = LoggerFactory.getLogger(SkyApplication.class);
    @Autowired
    private EmployeeService employeeService;

    @Test
    @DisplayName("修改管理员密码是否成功")
    public void testEditPasswodSuccess() {
        EmployPasswordDTO employPasswordDTO = new EmployPasswordDTO();
        employPasswordDTO.setEmpId(1);
        employPasswordDTO.setNewPassword("123456");
        employPasswordDTO.setOldPassword("456123");
        Boolean resBoolean = employeeService.editPassword(employPasswordDTO);
        logger.info("修改密码成功：{}", resBoolean);
    }

    @Test
    @DisplayName("修改管理员状态成功")
    public void testChangeStatusSuccess() {
        Boolean resBoolean = employeeService.changeStatus(1, 1);
        logger.info("修改管理员状态成功：{}", resBoolean);
    }

    @Test
    @DisplayName("修改管理员密码失败")
    public void testEditPasswodFail() {
        EmployPasswordDTO employPasswordDTO = new EmployPasswordDTO();
        employPasswordDTO.setEmpId(2);
        employPasswordDTO.setNewPassword("456789");
        employPasswordDTO.setOldPassword("123456");
        Boolean resBoolean = employeeService.editPassword(employPasswordDTO);
        logger.info("修改密码成功：{}", resBoolean);
    }

    @Test
    @DisplayName("修改管理员状态失败")
    public void testChangeStatusFail() {
        Boolean resBoolean = employeeService.changeStatus(1, 2);
        logger.info("修改管理员状态成功：{}", resBoolean);
    }
}
