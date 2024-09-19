package com.sky.exception;

import com.sky.constant.MessageConstant;

public class EmployeePhoneNotNullException extends FieldNotNullException{
    public EmployeePhoneNotNullException() {super(MessageConstant.EMPLOYEE_PHONE_NOT_NULL);}
}
