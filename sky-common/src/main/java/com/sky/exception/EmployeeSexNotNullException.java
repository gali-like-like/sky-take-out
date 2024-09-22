package com.sky.exception;

import com.sky.constant.MessageConstant;

public class EmployeeSexNotNullException extends FieldNotNullException{
    public EmployeeSexNotNullException() {super(MessageConstant.EMPLOYEE_SEX_NOT_NULL);}
}
