package com.sky.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sky.enums.ErrorCode;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * 后端统一返回结果
 *
 * @param <T>
 */
@Data
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
public class Result<T> implements Serializable {

    private Integer code; //编码：1成功，0和其它数字为失败
    private String msg; //错误信息
    private T data; //数据
    private Result(Integer code,String msg,T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static <T> Result<T> success() {
        Result<T> result = new Result<T>(1,null,null);
        return result;
    }

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>(1,null,object);
        return result;
    }

    public static <T> Result<T> success(String message,T object) {
        return new Result<>(1,message,object);
    }

    public static <T> Result<T> success(String message) {
        Result<T> result = new Result<>(1,message,null);
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result result = new Result(0,msg,null);
        return result;
    }

    public static <T> Result<T> error(ErrorCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMsg());
    }

    public static <T> Result<T> error(int code, String msg) {
        Result<T> result = new Result<>(0,msg,null);
        return result;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }
    public String toString() {
        return String.format("Result{code:%d,msg:%s,data:%s}",code,msg,data);
    }
}
