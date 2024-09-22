package com.sky.exception;

import com.sky.constant.MessageConstant;

public class EmployeeIDNumberNotNullException extends FieldNotNullException{
    public EmployeeIDNumberNotNullException() {super(MessageConstant.EMPLOYEE_IDNUMBER_NOT_NULL);}
}
