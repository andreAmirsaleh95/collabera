package com.collabera.jump.configuration;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket emsApi() {
		Predicate<RequestHandler> selector = RequestHandlerSelectors
				.basePackage("com.collabera.jump");

		Predicate<String> pathselector = PathSelectors.any();

		Docket docket = new Docket(DocumentationType.SWAGGER_2).select()
				.apis(selector).paths(pathselector).build();

		Collection<VendorExtension> extensions = new ArrayList<VendorExtension>();
		Contact contact = new Contact("Andre Amirsaleh",
				"https://www.linkedin.com/in/andre-amirsaleh-22254a97/",
				"andreamirsaleh95@gmail.com");
		ApiInfo apiInfo = new ApiInfo("EMS API",
				"Employee Management System REST API", "0.0.1",
				"google.com", contact, "license", "http://yahoo.com", extensions);

		docket.apiInfo(apiInfo);

		return docket;
	}

}
