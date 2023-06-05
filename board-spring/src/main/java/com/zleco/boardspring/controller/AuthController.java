package com.zleco.boardspring.controller;

import com.zleco.boardspring.dto.ResponseDto;
import com.zleco.boardspring.dto.SignUpDto;
import com.zleco.boardspring.dto.SignUpResponseDto;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(originPatterns="http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @PostMapping("/signUp")
    public ResponseDto<SignUpResponseDto> signUp(@RequestBody SignUpDto requestBody){
        System.out.println(requestBody.toString());
        return null;
    }

}
