package com.sky.exception;

import com.sky.constant.MessageConstant;

public class FieldNotNullException extends NullPointerException{
    public FieldNotNullException() {super(MessageConstant.EMPLOYEE_ID_NOT_NULL);}
    public FieldNotNullException(String msg) {super(msg);}
}
