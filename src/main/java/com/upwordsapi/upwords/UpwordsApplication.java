package com.upwordsapi.upwords;

import com.upwordsapi.upwords.solve.BoardSolver;
import com.upwordsapi.upwords.solve.BoardSolverFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class UpwordsApplication {

	public static void main(String[] args) {
		BoardSolverFactory.registerDefaultSupplier(BoardSolver.class.getName(), BoardSolver::new);
		SpringApplication.run(UpwordsApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer(){
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/upwords/solve").allowedOrigins("http://localhost:3000");
			}
		};
	}

}
