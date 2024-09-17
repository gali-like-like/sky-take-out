package com.sky.handler;

import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception ex) {
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * SpringMVC参数绑定，Validator校验不正确
     */
    @ExceptionHandler(BindException.class)
    public Result<String> bindException(BindException ex) {
        FieldError fieldError = ex.getFieldError();
        assert fieldError != null;
        return Result.error(fieldError.getDefaultMessage());
    }


}
