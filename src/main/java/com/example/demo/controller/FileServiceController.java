package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@RestController
public class FileServiceController {
    @RequestMapping(value = "/filelook", method = RequestMethod.GET)
    public ResponseEntity<Object> getFileInfo () throws IOException {
        File f = new File("src/main/resources/static/hello.txt");
        Reader in = new FileReader(f);
        String str = "";
        for (;;) {
            int n = in.read(); // 反复调用read()方法，直到返回-1
            if (n == -1) {
                break;
            }
            str += Character.toString(n);
        }
        in.close();
        System.out.println(str); // 打印byte的值
        return new ResponseEntity<>(str, HttpStatus.OK);
    }
}
