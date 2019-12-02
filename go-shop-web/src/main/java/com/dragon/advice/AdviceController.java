package com.dragon.advice;

import com.dragon.entity.base.Result;
import com.dragon.exception.NormalException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(NormalException.class)
    public Result<String> normalException(NormalException n){
        return Result.<String>builder().success().message(n.getMessage()).build();
    }



}
