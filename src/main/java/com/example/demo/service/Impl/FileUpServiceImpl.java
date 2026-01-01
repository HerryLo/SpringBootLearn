package com.example.demo.service.Impl;

import com.example.demo.service.FileUpService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class FileUpServiceImpl implements FileUpService {
    @Override
    public String getFileInfo() {
        String str = "";
        File f = new File("src/main/resources/static/hello.txt");
        try (InputStream in = new FileInputStream(f)) {
//            for (; ; ) {
//                int n = in.read(); // 反复调用read()方法，直到返回-1
//                if (n == -1) {
//                    break;
//                }
//                str += Character.toString(n);
//            }
            int n;
            while ( (n = in.read()) != -1) {
                str += Character.toString(n);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return str;
    }

    @Override
    public ResponseEntity writeFile(String content) throws FileNotFoundException {
        if (content == null || content.trim().isEmpty()) {
            return new ResponseEntity<>("请填写内容", HttpStatus.NOT_FOUND);
        }
        try (OutputStream output = new FileOutputStream("src/main/resources/static/hello.txt")) {
            output.write(content.getBytes(StandardCharsets.UTF_8)); // Hello
        } catch (IOException e) {
            throw new RuntimeException(e);
        } // 编译器在此自动为我们写入finally并调用close()
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @Override
    public void uploadFile(MultipartFile file) throws IOException {
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
    }
}
