package guru.springframework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors
				.withClassAnnotation(RestController.class))
				.paths(PathSelectors.any())
				.build()
				.tags(new Tag("Beer", "These endpoints are used to manage the Beer functionalities.", 1),
					  new Tag("Customer", "These endpoints are used to manage the Customer functionalities.", 2));

	}

	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
              .title("My Test Library API")
              .description("This is the mock Library API can be used to practise the swagger documentation")
              .version("1.0.0")
              .build();
    }
}