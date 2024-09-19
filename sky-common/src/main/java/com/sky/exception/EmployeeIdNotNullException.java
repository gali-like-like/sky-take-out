package com.sky.exception;

import com.sky.constant.MessageConstant;

public class EmployeeIdNotNullException extends FieldNotNullException{
    public EmployeeIdNotNullException() {super(MessageConstant.EMPLOYEE_ID_NOT_NULL);}
}
