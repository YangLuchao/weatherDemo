package com.ylc.weatherdemo.base.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

    protected IResponseEnum responseEnum;

    public BaseException(IResponseEnum responseEnum){
        super(responseEnum.getResultMsg());
        this.responseEnum = responseEnum;
    }
}
