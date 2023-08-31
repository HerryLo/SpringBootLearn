package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileServiceController {
    @RequestMapping(value = "/filelook", method = RequestMethod.GET)
    public ResponseEntity<Object> getFileInfo () {
        return new ResponseEntity<>("13123", HttpStatus.OK);
    }
}
