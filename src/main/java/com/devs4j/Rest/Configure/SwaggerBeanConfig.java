package com.devs4j.Rest.Configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration 
@EnableSwagger2
public class SwaggerBeanConfig {
	
	@Bean
	public Docket getDocket() {
		 return new Docket(DocumentationType.SWAGGER_2)
				 	.apiInfo(getApiInfo())
	                .select()
	              //.apis(RequestHandlerSelectors.any()) //Asi se muestran todos los controller
	                .apis(RequestHandlerSelectors.basePackage("com.devs4j.Rest.Controller"))//se pone el paquete a exponer
	               //.paths(PathSelectors.any())// asi se muestran todas las operaciones
	                .paths(PathSelectors.ant("/users/*")) // se espesifica el cotroller a exponer
	                .build();
	                 
	}

	private ApiInfo getApiInfo() {
		// TODO Auto-generated method stub
		return new ApiInfoBuilder()
				.title("Los servicios más chidos")
				.description("Esta es una descripcion de lo que hacen los servicios")
				.version("1.0")
				.license("La licenica")
				.contact(new Contact("Erwin","www.pagina.com", "correo@coore.com"))
				.build();
	}
	
}
