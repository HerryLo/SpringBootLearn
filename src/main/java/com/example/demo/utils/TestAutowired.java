package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestAutowired {
    public TestAutowired() {
        log.info("构造函数初始化");
    }

    public void print() {
        log.info("test");
    }
}
