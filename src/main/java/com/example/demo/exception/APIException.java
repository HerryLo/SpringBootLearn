package com.example.demo.exception;

import com.example.demo.response.AppCode;
import com.example.demo.response.StatusCode;
import lombok.Getter;

@Getter
public class APIException extends RuntimeException{

    private int code;
    private  String msg;

    public APIException(StatusCode statecode, String message) {
        super(message);
        this.code = statecode.getCode();
        this.msg = statecode.getMsg();
    }

    public APIException(String message) {
        super(message);
        this.code = AppCode.APPERROR.getCode();
        this.msg = AppCode.APPERROR.getMsg();
    }

	public Object getMsg() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getCode() {
		// TODO Auto-generated method stub
		return null;
	}
}
