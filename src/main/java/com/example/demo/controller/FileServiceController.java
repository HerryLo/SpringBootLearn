package com.example.demo.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class FileServiceController {
    @RequestMapping(value = "/filelook", method = RequestMethod.GET)
    public ResponseEntity<Object> getFileInfo() throws IOException {
        String str = "";
        File f = new File("src/main/resources/static/hello.txt");
        try (InputStream in = new FileInputStream(f)) {
            for (; ; ) {
                int n = in.read(); // 反复调用read()方法，直到返回-1
                if (n == -1) {
                    break;
                }
                str += Character.toString(n);
            }
            System.out.println(str); // 打印byte的值
        }
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    @RequestMapping(value = "filewrite", method = RequestMethod.POST)
    public ResponseEntity<Object> writeFile(String content) throws IOException {
        if (content == null || content.trim().isEmpty()) {
            return new ResponseEntity<>("请填写内容", HttpStatus.NOT_FOUND);
        }
        try (OutputStream output = new FileOutputStream("src/main/resources/static/hello.txt")) {
            output.write(content.getBytes(StandardCharsets.UTF_8)); // Hello
        } // 编译器在此自动为我们写入finally并调用close()
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        try (InputStream is = file.getInputStream();
             ByteArrayOutputStream os = new ByteArrayOutputStream();) {
            IOUtils.copy(is, os);
            byte[] bytes = os.toByteArray();

            String fileName = file.getOriginalFilename();
            String path = "src/main/resources/static/img/";

            try (FileOutputStream os1 = new FileOutputStream(path + fileName);
                 ByteArrayInputStream is1 = new ByteArrayInputStream(bytes)) {
                IOUtils.copy(is1, os1);
            }
        }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    ;
}
