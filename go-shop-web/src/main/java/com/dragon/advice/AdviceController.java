package com.dragon.advice;

import com.dragon.exception.NormalException;
import com.dragon.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(NormalException.class)
    public Result<String> normalException(NormalException n){
        return Result.<String>builder().code(HttpStatus.OK.value()).message(n.getMessage()).build();
    }



}
