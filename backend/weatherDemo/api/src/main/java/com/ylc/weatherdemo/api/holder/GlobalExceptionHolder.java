package com.ylc.weatherdemo.api.holder;

import com.ylc.weatherdemo.api.controller.response.ResponseCommand;
import com.ylc.weatherdemo.base.exception.BaseExceptionEnum;
import com.ylc.weatherdemo.base.exception.BaseRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHolder {

    @ExceptionHandler(BaseRuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseCommand baseRuntimeException(BaseRuntimeException e){
        return ResponseCommand.error(e.getResponseEnum());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object runtimeExceptionHolder(Throwable e){
        log.error("system error;" , e);
        return ResponseCommand.error(BaseExceptionEnum.SERVER_ERROR);
    }
}
