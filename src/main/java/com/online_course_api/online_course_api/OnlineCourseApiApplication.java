package com.online_course_api.online_course_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class OnlineCourseApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineCourseApiApplication.class, args);
		System.out.println("INside main");
	}
	
//	  @Bean
//	    public WebMvcConfigurer corsConfigurer() {
//	        return new WebMvcConfigurer() {
//	            @Override
//	            public void addCorsMappings(CorsRegistry registry) {
//	                registry.addMapping("/api/**")
//	                        .allowedOrigins("http://localhost:3000") // Replace with your frontend URL
//	                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//	                        .allowedHeaders("*")
//	                        .allowCredentials(true);
//	            }
//	        };
//	    }

}
