package com.example.demo.controller;

import com.example.demo.pojo.Result;
import com.example.demo.service.Impl.FileUpServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class FileUpController {

    public FileUpServiceImpl FileUpService;

    public FileUpController(FileUpServiceImpl FileUpService) {
        this.FileUpService = FileUpService;
    }

    @RequestMapping(value = "/filelook", method = RequestMethod.GET)
    public ResponseEntity<Object> getFileInfo() {
        String str1 = FileUpService.getFileInfo();
        return new ResponseEntity<>(Result.success(str1), HttpStatus.OK);
    }

    @RequestMapping(value = "filewrite", method = RequestMethod.POST)
    public ResponseEntity<Object> writeFile(String content) throws IOException {
        FileUpService.writeFile(content);
        return new ResponseEntity<>(Result.success(content), HttpStatus.OK);
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        FileUpService.uploadFile(file);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    ;
}
