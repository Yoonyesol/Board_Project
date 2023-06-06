package com.zleco.boardspring.service;

import com.zleco.boardspring.dto.ResponseDto;
import com.zleco.boardspring.dto.SignUpDto;
import com.zleco.boardspring.entity.UserEntity;
import com.zleco.boardspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service    //Service로 사용하겠다
public class AuthService {
    @Autowired UserRepository userRepository;
    public ResponseDto<?> signUp(SignUpDto dto){
        String userEmail = dto.getUserEmail();
        String userPassword = dto.getUserPassword();
        String userPasswordCheck = dto.getUserPasswordCheck();

        //email 중복 확인
        try{
            if (userRepository.existsById(userEmail))
                return  ResponseDto.setFailed("Existed Email!");
        } catch (Exception e){  //DB접근 오류 발생 시
            return  ResponseDto.setFailed("Data Base Error!");
        }

       //비밀번호가 서로 다르면 fail response 반환
        if(!userPassword.equals(userPasswordCheck))
            return ResponseDto.setFailed("Password does not matched!");

        //UserEntity 생성
        UserEntity userEntity = new UserEntity(dto);

        //userRepository를 이용해 DB에 Entity 저장
        try{
            userRepository.save(userEntity);
        } catch (Exception e){
            return ResponseDto.setFailed("Data Base Error!");
        }

        //성공 시 success response 반환
       return ResponseDto.setSuccess("SignUp Success!", null);
    }
}
