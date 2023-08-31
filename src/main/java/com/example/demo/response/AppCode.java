package com.example.demo.response;

import lombok.Getter;

@Getter
public enum AppCode implements StatusCode {
    APPERROR(2000, "业务异常"),
    PRICEERROR(2001, "价格异常");

    private int code;
    private String msg;

    AppCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
