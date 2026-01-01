package com.example.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileUpService {
    String getFileInfo();
    ResponseEntity<String> writeFile(String content)  throws FileNotFoundException;
    void uploadFile(MultipartFile file) throws IOException;
}
