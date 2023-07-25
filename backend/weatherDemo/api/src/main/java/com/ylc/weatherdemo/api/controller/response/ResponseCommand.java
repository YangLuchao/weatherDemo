package com.ylc.weatherdemo.api.controller.response;

import com.ylc.weatherdemo.base.exception.IResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCommand implements Serializable {

    private Integer code;

    private String message;

    private Object result;

    public static ResponseCommand success(Object data){
        ResponseCommand responseCommand = new ResponseCommand();
        responseCommand.setCode(200);
        responseCommand.setMessage("success");
        if (Objects.isNull(data)){
            responseCommand.setResult(new HashMap<>());
        } else {
            responseCommand.setResult(data);
        }
        return responseCommand;
    }
    public static ResponseCommand success(){
        return success(null);
    }

    public static ResponseCommand error(IResponseEnum error){
        ResponseCommand responseCommand = new ResponseCommand();
        responseCommand.setCode(error.getResultCode());
        responseCommand.setMessage(error.getResultMsg());
        return responseCommand;
    }
}

