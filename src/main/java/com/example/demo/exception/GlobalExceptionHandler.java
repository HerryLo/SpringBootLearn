package com.example.demo.exception;  // 新建包

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.demo.pojo.Result;  // 导入你的Result类

@Slf4j
@RestControllerAdvice  // 这个注解是关键
public class GlobalExceptionHandler {

    // 拦截参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidationExceptions(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        log.warn("参数校验失败：{}", message);
        return Result.error(message);
    }

    // 拦截所有其他异常
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("系统异常", e);
        return Result.error(e.getMessage());
    }
}