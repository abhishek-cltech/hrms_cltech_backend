package com.cltech.hrms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EnableJpaRepositories
public class HrmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrmsApplication.class, args);
		System.out.println("--------->"+System.getProperty("java.class.path"));
		
//		String str="Abrar";
//		String str2="";
//		for(int i=str.length()-1;i>=0;i--) {
//			str2+=str.charAt(i);
//		}
//		System.out.println(str2);
	}
	@Bean
	protected  WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
				.addMapping("/**")
				.allowedMethods("*")
				.allowedOrigins("*")
                .allowedHeaders("*");
				//.exposedHeaders("Authorization")
				//.allowCredentials(true)
               // .maxAge(4800L);
			}
		};
	}

}
