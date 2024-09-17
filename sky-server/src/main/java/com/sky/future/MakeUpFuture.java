package com.sky.future;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Component
public class MakeUpFuture {

    public Object makeUpHandle(Object res, Throwable e) {
        if(Objects.isNull(e))
            return res;
        log.error("发生异常:\n{}",e.getLocalizedMessage());
        return null;
    }

    public Result makeUpBoolFuture(
            CompletableFuture<Boolean> future,
            String successMsg,
            String systemErrorMsg,String failMsg) throws ExecutionException, InterruptedException, TimeoutException {
        Boolean res = future.get(3, TimeUnit.SECONDS);
        if(Objects.isNull(res))
            return Result.error(systemErrorMsg);
        else if(res)
            return Result.success();
        else
            return Result.error(failMsg);
    }
}
