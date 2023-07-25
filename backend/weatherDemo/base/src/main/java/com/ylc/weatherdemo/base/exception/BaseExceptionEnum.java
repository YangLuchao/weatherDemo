package com.ylc.weatherdemo.base.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseExceptionEnum implements IResponseEnum {
    SERVER_ERROR(500, "service_error"),

    NOT_LOCATION(501, "address does not exist"),

    TIME_OUT(502, "time out"),

    TOKEN_ERROR(503, "token 配置异常")
    ;
    private Integer resultCode;

    private String resultMsg;
}
