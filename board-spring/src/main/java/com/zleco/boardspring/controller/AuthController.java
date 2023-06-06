package com.zleco.boardspring.controller;

import com.zleco.boardspring.dto.ResponseDto;
import com.zleco.boardspring.dto.SignUpDto;
import com.zleco.boardspring.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired AuthService autoService;
    @PostMapping("/signUp")
    public ResponseDto<?> signUp(@RequestBody SignUpDto requestBody){
        ResponseDto<?> result = autoService.signUp(requestBody);
        return result;
    }

}
