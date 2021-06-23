package com.a105.apiServer.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Predicate;

import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

//http://localhost:8877/a407/swagger-ui.html
//https://i4a407.p.ssafy.io:8877/a407/swagger-ui.html

	private String version = "V1.1";
	private String title = "A105 API " + version;
	
	@Bean
	public Docket api() {
		List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
		responseMessages.add(new ResponseMessageBuilder().code(200).message("Success").build());
		responseMessages.add(new ResponseMessageBuilder().code(500).message("Server Error").responseModel(new ModelRef("Error")).build());
		responseMessages.add(new ResponseMessageBuilder().code(404).message("Page Not Found").build());
		
		return new Docket(DocumentationType.SWAGGER_2)
					.apiInfo(apiInfo())
					.groupName(version)
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.a105.apiServer.controller"))
					.paths(postPaths()).build()
					.useDefaultResponseMessages(false)
					.globalResponseMessage(RequestMethod.GET,responseMessages)
					.securityContexts(Arrays.asList(securityContext()))
					.securitySchemes(Arrays.asList(apiKey()));
	}
	
	private Predicate<String> postPaths() {
		return PathSelectors.any();
//		return or(regex("/user/.*"), regex("/article/.*"), regex("/memo/.*"));
//		return regex("/admin/.*");
	}
//
	private ApiKey apiKey() {
		return new ApiKey("x-access-token", "x-access-token", "header");
	}
	
	private SecurityContext securityContext() {
		return springfox
			.documentation
			.spi
			.service
			.contexts
			.SecurityContext
			.builder()
			.securityReferences(defaultAuth())
			.forPaths(PathSelectors.any())
			.build();
	}
	
	List<SecurityReference> defaultAuth(){
		AuthorizationScope aScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] aScopes = new AuthorizationScope[1];
		aScopes[0] = aScope;
		return Arrays.asList(new SecurityReference("x-access-token", aScopes));
	}
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(title)
				.description("<h1>Steam World</h1>") 
				.version("1.1").build();
	}
}