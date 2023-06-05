package com.zleco.boardspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BoardSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardSpringApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry){
				//모든 경로에 대해 CORS 허용
				registry.addMapping("/**").allowedOriginPatterns();
			}
		};
	}
}
