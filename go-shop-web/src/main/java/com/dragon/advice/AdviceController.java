package com.dragon.advice;

import com.dragon.exception.NormalException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(NormalException.class)
    public Result<String> normalException(NormalException n){
        return Result.<String>builder().success().message(n.getMessage()).build();
    }



}
