package com.zleco.boardspring.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenProvider {
    //jwt 생성 및 검증 시 사용할 키 생성-> 해당 키를 기반으로 데이터 암/복호화
    private static final String SECURITY_KEY = "jwtseckey!@";

    //jwt 생성 메서드
    public String create(String userEmail){
        //인증만료 시간 지정 = 현재날짜 + 1시간
        Date exprTime = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        //JWT 생성
        return Jwts.builder()
                //암호화에 사용될 알고리즘, 키
                .signWith(SignatureAlgorithm.HS256, SECURITY_KEY)
                //JWT 제목, 생성일, 만료일
                .setSubject(userEmail).setIssuedAt(new Date()).setExpiration(exprTime)  //필요한 데이터 우선 userEmail만 추가한 상태
                //JWT 생성
                .compact(); //jwt완료
    }

    //JWT 검증(복호화) 메서드
    public String validate(String token){
        //키를 사용해 매개변수로 받은 token을 복호화
        Claims claims = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody();
        //복호화된 토큰의 payload에서 제목을 가져옴
        return claims.getSubject(); //setSubject에 넣었던 데이터(userEmail)를 가져올 수 있다.
    }

}
