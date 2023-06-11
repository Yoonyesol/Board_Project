package com.zleco.boardspring.config;

import com.zleco.boardspring.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                //cors 정책(현재는 Application에서 작업을 해뒀으므로 기본 설정 사용)
                .cors().and()
                //csrf(사이트간 요청위조)대책(현재는 비활성화)
                .csrf().disable()
                //Basic 인증(현재는 Bearer token 인증방법을 사용하므로 비활성화)
                .httpBasic().disable()
                //세션 사용(현재는 Session기반 인증 사용하지 않기 때문에 상태를 없앰
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //"/", "/api/auth/**" 모듈에 대해서는 모두 허용(인증없이 사용가능)
                .authorizeRequests().antMatchers("/", "/api/auth/**").permitAll()
                //나머지 요청에 대해서는 모두 인증된 사용자만 사용가능하게 함
                .anyRequest().authenticated();

        //httpSecurity를 필터에 추가
        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
