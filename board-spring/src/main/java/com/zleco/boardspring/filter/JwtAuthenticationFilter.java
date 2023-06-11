package com.zleco.boardspring.filter;

import com.zleco.boardspring.security.TokenProvider;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerErrorException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // request가 들어왔을 때 Request Header의 Authorization 필드의 Bearer Token을 가져옴
    //가져온 토큰을 검증하고 검증 결과를 SecurityContext에 추가-> 해당 스레드가 지속적으로 내용 추적 가능

    @Autowired private TokenProvider tokenProvider;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServerErrorException, IOException, ServletException {
        try{
            String token = parseBearerToken(request);

            //토큰이 null이 아니거나 대소문자 상관없이 문자열로 null이 아닐 경우
            if(token != null && !token.equalsIgnoreCase("null")){
                //토큰을 검증해서 payload의 userEmail 가져옴
                String userEmail = tokenProvider.validate(token);

                //SecurityContext에 추가할 객체 생성
                AbstractAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userEmail, null, AuthorityUtils.NO_AUTHORITIES);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //SecurityContext에 AbstractAuthenticationToken객체를 추가해서
                // 해당 스레드가 지속적으로 인증정보를 가질 수 있도록 해줌
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();   //빈 컨텐스트 생성
                securityContext.setAuthentication(authentication);
                SecurityContextHolder.setContext(securityContext);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        filterChain.doFilter(request, response);
    }

    //Request Header의 Authorization 필드의 Bearer Token을 가져오는 메서드
    private String parseBearerToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");

        ////bearerToken이 빈값이나 null이 아닌지 확인 && Authorization이 bearerToken인지 확인
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);    //7자리 글자부터 받아오기
        }
        return null;
    }
}
