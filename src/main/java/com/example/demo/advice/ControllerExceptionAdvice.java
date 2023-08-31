package com.example.demo.advice;

import com.example.demo.exception.APIException;
import com.example.demo.response.ResultCode;
import com.example.demo.response.ResultVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;

@RestControllerAdvice
@ResponseStatus(HttpStatus.OK)
@ResponseBody
public class ControllerExceptionAdvice {
    @ExceptionHandler({BindException.class})
    public ResultVO MethodArgumentNotValidExceptionHandler(BindException e) {
        String error = String.valueOf(e.getStackTrace()[0]);
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, error);
    }

    @ExceptionHandler(APIException.class)
    public ResultVO APIExceptionHandler(APIException e) {
        return new ResultVO<>(e.getCode(), e.getMsg(), e.getMessage());
    }
}
