package com.zleco.boardspring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")	//url이 존재하지 않을 때 MainController 실행
public class MainController {
    @GetMapping("")
    public String hello(){
        return "Connection Success!";
    }
}
