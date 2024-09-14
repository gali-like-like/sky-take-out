package com.sky.controller.admin;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.github.pagehelper.PageInfo;
import com.sky.dto.EmployeePageQueryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sky.constant.JwtClaimsConstant;
import com.sky.constant.MessageConstant;
import com.sky.dto.EmployPasswordDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Api(tags="员工管理的接口")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;
    
    private Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    
    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @ApiOperation(value="员工登录",notes = "员工使用账号和密码进行登录")
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@ApiParam(name = "包含了员工用户名,员工的密码",required = true)  @RequestBody EmployeeLoginDTO employeeLoginDTO) {
        Employee employee = employeeService.login(employeeLoginDTO);
        logger.info("员工登录：{}", employeeLoginDTO);
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder(employee.getId(), employee.getUsername(), employee.getName(), token);
        return Result.success(employeeLoginVO);
    }

    @ApiOperation(value = "修改密码",notes="员工用id来修改密码")
    @PutMapping("/editPassword")
    public Result editPassword(@ApiParam(name="包含了员工id,员工的新密码,员工的旧密码",required = true)  @RequestBody EmployPasswordDTO passwordDTO) throws InterruptedException, ExecutionException, TimeoutException {
		CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(()-> {
			return employeeService.editPassword(passwordDTO);
		}).handle((res,e) -> {
			if(Objects.isNull(e)) {
				return res;
			}
			logger.error("发生异常,具体信息:\n{}",e.getLocalizedMessage());
			return null;
		});
		Boolean resBoolean = future.get(3,TimeUnit.SECONDS);
        //防止server里头发生了异常,resBoolean就会为空
		if(Objects.nonNull(resBoolean) && resBoolean.equals(true))
			return Result.success();
		else
			return Result.error(MessageConstant.ACCOUNT_NOT_FOUND);
    }
    
    @ApiOperation(value = "修改状态",notes="员工修改状态")
    @PostMapping("/status/{status}")
    public Result changeStatus(@ApiParam(name="status",required = true) @PathVariable Integer status,
    		@ApiParam(name="id",required = true) @RequestParam Integer id) throws InterruptedException, ExecutionException, TimeoutException {
    	CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(()->{
    		return employeeService.changeStatus(status, id);
    	}).handle((res,e)-> {
    		if(Objects.isNull(e)) {
				return res;
			}
			logger.error("发生异常,具体信息:\n{}",e.getLocalizedMessage());
			return null;
    	});
    	Boolean resBoolean = future.get(3,TimeUnit.SECONDS);
    	if(Objects.nonNull(resBoolean)) {
    		return Result.success();
    	}
    	else
			return Result.error(MessageConstant.ACCOUNT_NOT_FOUND);
    }

    @ApiOperation(value="分页查询",notes="根据当前查询当前包含的数据")
    @GetMapping("/page")
    public Result pageDataByPageNum(@ApiParam(required = true) EmployeePageQueryDTO queryDTO) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<PageInfo<Employee>> future = CompletableFuture.supplyAsync(() ->{
           return employeeService.pageDataByPageSize(queryDTO);
        }).handle((res,e) -> {
            if(Objects.isNull(e)) {
                return res;
            }
            logger.info("发生异常:\n{}",e.getLocalizedMessage());
            return null;
        });
        PageInfo<Employee> employeePageInfo = future.get(3,TimeUnit.SECONDS);
        if(Objects.nonNull(employeePageInfo)) {
            return Result.success(employeePageInfo);
        }
        else
            return Result.error("查询异常");
    }
    
    
    /**
     * 退出
     *
     * @return
     */
    @ApiOperation(value="退出登录")
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

}
