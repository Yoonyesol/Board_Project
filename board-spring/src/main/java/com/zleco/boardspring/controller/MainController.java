package com.zleco.boardspring.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(originPatterns="http://localhost:3000") //3000번 허용
@RestController
@RequestMapping("/")	//url이 존재하지 않을 때 MainController 실행
public class MainController {
    @GetMapping("")
    public String hello(){
        return "Connection Success!";
    }
}
