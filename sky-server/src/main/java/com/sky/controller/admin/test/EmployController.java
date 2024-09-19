package com.sky.controller.admin.test;

import com.sky.result.Result;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@Validated
public class EmployController {

    private final HttpMessageConverters messageConverters;

    public EmployController(HttpMessageConverters messageConverters) {
        this.messageConverters = messageConverters;
    }

    @GetMapping("/test")
    public Result<?> test(@RequestParam @NotNull(message = "not null") Long id){
        return Result.success(id);
    }
}
