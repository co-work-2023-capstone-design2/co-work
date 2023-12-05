/*
package com.example.cowork.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping(value="/api/test", produces="application/json;charset=UTF-8")
public class testController {
    @GetMapping(value="/Korean", produces="application/json;charset=UTF-8")
    public ResponseEntity<?> Korean(){

        // Header 추가 설정
        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.add("Content-Type", "application/json;charset=UTF-8");
//        String temp = "한국어 테스트";
//        System.out.println(temp);


//        return ResponseEntity
//                .ok()
//                .headers(resHeaders)
//                .body("test: 한국어 테스트");

        return new ResponseEntity<>("한국어 테스트", resHeaders, HttpStatus.OK);
    }

}
*/
