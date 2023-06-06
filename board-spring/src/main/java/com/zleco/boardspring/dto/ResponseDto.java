package com.zleco.boardspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "set") //set이라는 이름으로 사용
public class ResponseDto<D> {
    private boolean result;
    private String message;
    private D data; //어떤 타입이 올지 확실치 않으므로 제네릭 타입으로 생성

    //데이터 받아오기 성공 시 실행할 코드
    public static <D> ResponseDto<D> setSuccess(String message, D data){
        //return new ResponseDto<>(true, message, data); 아래 코드와 동일한 동작하는 코드
        return ResponseDto.set(true, message, data);
    }

    //실패 시 실행할 코드
    public static <D> ResponseDto<D> setFailed(String message){
        return ResponseDto.set(false, message, null);
    }
}
