package com.zleco.boardspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInDto {
    @NotBlank   //필수값 지정
    private String userEmail;
    @NotBlank
    private String userPassword;
}
