package com.sky.handler;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.junit.jupiter.api.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static com.sky.enums.ErrorCode.INTERNAL_SERVER_ERROR;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * SpringMVC参数绑定，Validator校验不正确
     */
    @ExceptionHandler(BindException.class)
    public Result<String> bindExceptionHandler(BindException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.info("error:{}",errorMessage);
        FieldError fieldError = ex.getFieldError();
        assert fieldError != null;
        return Result.error(errorMessage);
    }

    /**
     * TimeOut 捕获请求超时
     * */
    @ExceptionHandler(TimeoutException.class)
    public Result<String> timeoutHandler(TimeoutException e) {
        return Result.error(MessageConstant.TIME_OUT_ERROR);
    }
    //
    @ExceptionHandler(ExecutionException.class)
    public Result<String> executionHandler(ExecutionException e) {
        return Result.error(INTERNAL_SERVER_ERROR.getMsg());
    }
    //中断异常
    @ExceptionHandler(InterruptedException.class)
    public Result<String> executionHandler(InterruptedException e) {
        return Result.error(INTERNAL_SERVER_ERROR.getMsg());
    }

    //缺乏参数异常
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Result<String> handle2(MissingServletRequestParameterException e) {
        return Result.error("缺乏参数异常");
    }

    //验证异常
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result handle1(ConstraintViolationException ex) {

        StringBuilder msg = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();
            String paramName = pathImpl.getLeafNode().getName();
            String message = constraintViolation.getMessage();
            msg.append("[").append(message).append("]");
        }
//        logger.error(msg.toString(),ex);
        // 注意：Response类必须有get和set方法，不然会报错
        return Result.error(msg.toString());
    }

    // 捕获路径参数缺失的异常
    @ExceptionHandler(MissingPathVariableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 返回 400 错误
    public Result<?> handleMissingPathVariable(MissingPathVariableException ex) {
        return Result.error(ex.getVariableName());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        // 构建自定义的错误信息
        ex.printStackTrace();
        return Result.error(HttpStatus.METHOD_NOT_ALLOWED.value(), ex.getMessage());
    }


    /**
     * 捕获业务异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @Order(999)
    public Result exceptionHandler(Exception ex) {
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }
}
