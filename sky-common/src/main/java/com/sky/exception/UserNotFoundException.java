package com.sky.exception;

import com.sky.constant.MessageConstant;

public class UserNotFoundException extends BaseException{
    public UserNotFoundException() {
        super(MessageConstant.USER_NOT_FOUND);
    }

    public UserNotFoundException(String msg) {super(msg);}
}
