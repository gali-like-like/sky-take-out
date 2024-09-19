package com.sky.exception;

import com.sky.constant.MessageConstant;

public class EmployeeNameNotNullException extends FieldNotNullException{
    public EmployeeNameNotNullException() {super(MessageConstant.EMPLOYEE_NAME_NOT_NULL);}
}