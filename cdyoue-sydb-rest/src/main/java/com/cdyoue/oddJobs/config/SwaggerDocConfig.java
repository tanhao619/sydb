package com.cdyoue.oddJobs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerDocConfig {

	ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Swagger Odd Jobs")
				.description("This is rest API for Odd Jobs server.  For this sample, you can use the api key `special-key` to test the authorization filters ")
				.license("")
				.licenseUrl("http://unlicense.org")
				.termsOfServiceUrl("")
				.version("1.0.0")
				.contact(new Contact("","", ""))
				.build();
	}

	@Bean
	public Docket customImplementation(){


		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.cdyoue.oddJobs"))
				.build()
				.directModelSubstitute(org.joda.time.LocalDate.class, java.sql.Date.class)
				.directModelSubstitute(org.joda.time.DateTime.class, java.util.Date.class)
//				.globalOperationParameters(aParameters)
				.apiInfo(apiInfo());
	}

}
