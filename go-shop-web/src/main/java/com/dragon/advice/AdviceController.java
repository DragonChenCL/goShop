package com.dragon.advice;

import com.dragon.exception.NormalException;
import com.dragon.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(NormalException.class)
    public Result<String> normalException(HttpServletRequest request,NormalException ex){
        return Result.<String>builder().code(getStatus(request).value()).message(ex.getMessage()).build();
    }

    //参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> normalException(HttpServletRequest request,MethodArgumentNotValidException ex){
        if (ex.getBindingResult().hasErrors()){
            return Result.<String>builder().code(getStatus(request).value()).message(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage()).build();
        }
        return Result.<String>builder().code(getStatus(request).value()).message("参数错误").build();
    }

    @ExceptionHandler(Exception.class)
    public Result<String> exception(HttpServletRequest request,Exception ex){
        return Result.<String>builder().code(getStatus(request).value()).message(ex.getMessage()).build();
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
