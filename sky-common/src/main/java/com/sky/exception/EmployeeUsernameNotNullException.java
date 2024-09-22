package com.sky.exception;

import com.sky.constant.MessageConstant;

public class EmployeeUsernameNotNullException extends FieldNotNullException{
    public EmployeeUsernameNotNullException() {super(MessageConstant.EMPLOYEE_USERNAME_NOT_NULL);}
}
