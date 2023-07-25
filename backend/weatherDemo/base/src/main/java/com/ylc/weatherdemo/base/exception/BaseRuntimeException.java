package com.ylc.weatherdemo.base.exception;

public class BaseRuntimeException extends BaseException {
    public BaseRuntimeException(IResponseEnum responseEnum) {
        super(responseEnum);
    }

    public static BaseRuntimeException getInstance(IResponseEnum responseEnum){
        return new BaseRuntimeException(responseEnum);
    }

    public static BaseRuntimeException sendErrorMessage(String message){
        return new BaseRuntimeException(sendMessage(message));
    }

    private static IResponseEnum sendMessage(String message){
        return new IResponseEnum() {
            @Override
            public Integer getResultCode() {
                return 500;
            }

            @Override
            public String getResultMsg() {
                return message;
            }
        };
    }
}
