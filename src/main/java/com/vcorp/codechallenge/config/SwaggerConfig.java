package com.vcorp.codechallenge.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author VVEERAVELU
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {          
       
    @Bean
    public Docket api() {         
    	
    	 return new Docket(DocumentationType.SWAGGER_2)  
    	          .select()                                  
    	          .apis(RequestHandlerSelectors.any())              
    	          .paths(PathSelectors.any())                          
    	          .build();         
    }
    
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("Code challenge REST API", "API to expose the Code challenge details.", "V1.0", "Terms of service", new Contact("crt@ets.org", "www.ets.org", "crt@ets.org"), "License of API", "API license URL", Collections.emptyList());
        return apiInfo;
    }
}

