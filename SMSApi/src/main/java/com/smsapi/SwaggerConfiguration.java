package com.smsapi;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	@Autowired
	private Messages messages;

    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
        	.apiInfo(apiInfo())
        	.tags(
        			new Tag("SMS APIs","")
        		 )
        	.select()                                  
        	.apis(RequestHandlerSelectors.basePackage("com.smsapi"))             
            .build();                                       
    }
    
    private ApiInfo apiInfo() {
    	return new ApiInfo(
    			messages.getMessage("swagger.title"), 
    			messages.getMessage("swagger.description"), 
    			null, 
    			null, 
    			null,  
    			"Apache License Version 2.0",
    			"http://www.apache.org/licenses/LICENSE-2.0",
    			Collections.emptyList());
    }
    
    @Bean
    public WebMvcConfigurer forwardToIndex() {
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
            	registry.addViewController("/").setViewName("redirect:/swagger-ui.html");
            }
        };
    }	

}
