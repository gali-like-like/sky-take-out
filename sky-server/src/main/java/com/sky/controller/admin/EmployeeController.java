package com.sky.controller.admin;

import com.github.pagehelper.PageInfo;
import com.sky.constant.JwtClaimsConstant;
import com.sky.constant.MessageConstant;
import com.sky.dto.EmployPasswordDTO;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.FieldNotNullException;
import com.sky.properties.JwtProperties;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Api(tags = "员工管理的接口")
@Validated
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;
    @Resource(name="taskThreadPool")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    private Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @ApiOperation(value = "员工登录", notes = "员工使用账号和密码进行登录")
    @PostMapping("/login")
    // todo 优化错误返回结果
    public Result<EmployeeLoginVO> login(@ApiParam(name = "包含了员工用户名,员工的密码",
            required = true) @Validated @RequestBody EmployeeLoginDTO employeeLoginDTO) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<HashMap<String,Object>> future = CompletableFuture.supplyAsync(()->{
             return employeeService.login(employeeLoginDTO);
        }).handle((res, e)->{
            if(Objects.isNull(e)) {
                return res;
            }
            logger.info("发生异常:{}",e.getLocalizedMessage());
            HashMap<String,Object> exceptionData = new HashMap<>();
            exceptionData.put("msg",MessageConstant.SYSTEM_ERROR);
            exceptionData.put("data",e.getLocalizedMessage());
            return exceptionData;

        });

        HashMap<String,Object> resultData = future.get(3,TimeUnit.SECONDS);
        logger.info("员工登录：{}", resultData.get("data"));
        //获取数据
        Object data = resultData.get("data");
        if(Objects.isNull(data))
            return Result.error((String) resultData.get("msg"));

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        Employee employee = (Employee) data;
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder(employee.getId(), employee.getUsername(), employee.getName(), token);
        return Result.success((String)resultData.get("msg"),employeeLoginVO);
    }

    @ApiOperation(value = "修改密码", notes = "员工用id来修改密码")
    @PutMapping("/editPassword")
    public Result editPassword(@ApiParam(name = "包含了员工id,员工的新密码,员工的旧密码", required = true) @Validated(EmployPasswordDTO.inter1.class) @RequestBody EmployPasswordDTO passwordDTO) throws InterruptedException, ExecutionException, TimeoutException {
        CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
            return employeeService.editPassword(passwordDTO);
        },threadPoolTaskExecutor).handle((res, e) -> {
            if (Objects.isNull(e)) {
                return res;
            }
            logger.info("发生异常:\n{}", e.getLocalizedMessage());
            return null;
        });
        Boolean resBoolean = future.get(3, TimeUnit.SECONDS);
        //防止server里头发生了异常,resBoolean就会为空
        if (Objects.nonNull(resBoolean) && resBoolean.equals(true))
            return Result.success();
        else
            return Result.error(MessageConstant.ACCOUNT_NOT_FOUND);
    }

    @ApiOperation(value = "修改状态", notes = "员工修改状态")
    @PostMapping("/status/{status}")
    public Result changeStatus(@PathVariable @Min(value = 0, message = "状态值不能小于0") @Max(value = 1, message = "状态值不能大于1") Integer status,
                                @RequestParam @NotNull(message = "员工编号不能为空")  Long id) throws InterruptedException, ExecutionException, TimeoutException {
        CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
            return employeeService.changeStatus(status, id);
        },threadPoolTaskExecutor).handle((res, e) -> {
            if (Objects.isNull(e))
                return res;
            logger.info("发生异常:\n{}", e.getLocalizedMessage());
            return null;
        });
        Boolean resBoolean = future.get(3, TimeUnit.SECONDS);
        if (Objects.nonNull(resBoolean))
            return Result.success();
        else
            return Result.error(MessageConstant.ACCOUNT_NOT_FOUND);
    }

    @ApiOperation(value = "修改状态", notes = "员工修改状态")
    @PostMapping("/status/")
    public Result changeStatusNull(@NotNull(message = "员工编号不能为空") @RequestParam Long id) {
        return Result.error("状态不能为空");
    }


    @ApiOperation(value = "分页查询", notes = "根据当前查询当前包含的数据")
    @GetMapping("/page")
    //全部字段都不为空
    public Result pageDataByPageNum(@ApiParam(required = true) @RequestParam EmployeePageQueryDTO queryDTO) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<PageInfo<Employee>> future = CompletableFuture.supplyAsync(() -> {
            return employeeService.pageDataByPageSize(queryDTO);
        },threadPoolTaskExecutor).handle((res, e) -> {
            if (Objects.isNull(e)) {
                return res;
            }
            logger.info("发生异常:\n{}", e.getLocalizedMessage());
            return null;
        });
        PageInfo<Employee> employeePageInfo = future.get(3, TimeUnit.SECONDS);
        if (Objects.nonNull(employeePageInfo)) {
            return Result.success(employeePageInfo);
        } else
            return Result.error("查询异常");
    }

    @ApiOperation(value = "新增员工", notes = "根据用户信息添加员工")
    @PostMapping("")
    public Result employee(@ApiParam(required = true) @Validated(EmployeeDTO.GroupAdd.class) @RequestBody EmployeeDTO employeeDTO) throws InterruptedException, ExecutionException, TimeoutException {
        CompletableFuture<String> future = CompletableFuture.runAsync(() -> {
            employeeService.addEmployee(employeeDTO);
        },threadPoolTaskExecutor).handle((res, e) -> {
            if (Objects.isNull(e)) {
                return MessageConstant.EMPLOYEE_ADD_SUCCESS;
            }
            else{
                logger.info("发生异常:\n{}", e.getLocalizedMessage());

                if(e.getCause() instanceof DuplicateKeyException)
                    return MessageConstant.EMPLOYEE_USERNAME_DUPLICATION;
                else
                    return MessageConstant.SYSTEM_ERROR;
            }
        });
        String res = future.get(3, TimeUnit.SECONDS);
        if (res.equals(MessageConstant.EMPLOYEE_ADD_SUCCESS))
            return Result.success(res);
        return Result.error(res);
    }

    @ApiOperation(value = "查询用户", notes = "根据id查询用户")
    @GetMapping("/{id}")
    //@RequestParam 不能去
    public Result getEmployeeById(@ApiParam(value = "id", required = true) @RequestParam
                                      @PathVariable(required = false) Long id) throws ExecutionException, InterruptedException, TimeoutException, BindException,FieldNotNullException {
        CompletableFuture<HashMap<String,Object>> future = CompletableFuture.supplyAsync(() ->
                employeeService.getEmployeeById(id)
        ,threadPoolTaskExecutor).handle((res, e) -> {
            if (Objects.isNull(e))   {
                return res;
            }
            else {
                HashMap<String,Object> errorData = new HashMap<>();
                errorData.put("employ",null);
                errorData.put("msg",e.getCause().getMessage());
                return errorData;
            }
        });
        HashMap<String,Object> result = future.get(3, TimeUnit.SECONDS);
        if (Objects.nonNull(result.get("employ")))
            return Result.success((String) result.get("msg"),result.get("employ"));
        else
            return Result.error((String) result.get("msg"));
    }

    @PutMapping("")
    @ApiOperation(value = "编辑员工信息")
    public Result editEmployee(@ApiParam(required = true) @Validated(EmployeeDTO.GroupUpdate.class) @RequestBody EmployeeDTO employeeDTO) throws InterruptedException, ExecutionException, TimeoutException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() ->
                employeeService.updateEmployee(employeeDTO)
        ,threadPoolTaskExecutor).handle((res, e) -> {
            if (Objects.isNull(e))
                return res;
            logger.info("发生异常:\n{}", e.getLocalizedMessage());
            if(e.getCause() instanceof AccountNotFoundException)
                return MessageConstant.ACCOUNT_NOT_FOUND;
            else if(e.getCause() instanceof FieldNotNullException) {
                return e.getCause().getMessage();
            }
            return e.getMessage();
        });
        String result = future.get(3,TimeUnit.SECONDS);
        logger.info("result:{}",result);
        return result.equals(MessageConstant.EMPLOYEE_UPDATE_SUCCESS)?Result.success(result):Result.error(result);
    }

    /**
     * 退出
     *
     * @return
     */
    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

    //处理布尔类型的future
    private Result makeUpFuture(CompletableFuture<Boolean> future) throws ExecutionException, InterruptedException, TimeoutException {
        Boolean res = future.get(3, TimeUnit.SECONDS);
        if (Objects.isNull(res)) {
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        } else {
            if (res)
                return Result.success();
            else
                return Result.error(MessageConstant.ACCOUNT_NOT_FOUND);
        }
    }
}
