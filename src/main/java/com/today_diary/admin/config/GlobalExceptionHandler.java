package com.today_diary.admin.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BaseException.class})
    protected BaseResponse handleBaseException(BaseException e){
        return new BaseResponse(e.getStatus());
    }
}
