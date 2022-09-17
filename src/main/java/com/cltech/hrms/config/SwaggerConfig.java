package com.cltech.hrms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
//@EnableSwagger2
public class SwaggerConfig  {
	
	//using springfox
//	@Bean
//    protected Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.cltech.hrms"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(getApiInfo());
//    }
//
//    private ApiInfo getApiInfo() {
//        return new ApiInfoBuilder()
//                .title("CLTech Employee Resume Api")
//                .description("The project is develped By clTech Team")
//                .version("1.0.0")
//                .contact(new Contact("Dev-Team", "https://www.dev-team.com/", "dev-team@gmail.com"))
//                .license("Apache 2.0")
//                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
//                .build();
//    }
    
	@Bean
  protected OpenAPI api(){
      return new OpenAPI().info(
    		  new Info()
    		  .title("Cltech Backend Api")
              .description("Employee Resume Api Made By ClTech Develper team")
              .version("0.0.1")
               .contact(new Contact().email("deveTeam@cltech.com").name("ClTech DevTeam"))
              .license(
               new License().name("Apache 2.0")
               .url("http://springdoc.org")))
              .externalDocs(new ExternalDocumentation()
              .description("Cltech backened Api using Spring boot")
              .url("https://github.com/Abrarkhan-786/cltecch_backened")
            );
  }
   

}
