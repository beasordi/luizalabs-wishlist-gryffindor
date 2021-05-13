package br.com.wishlist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.RequestHandlerSelectors;
import com.google.common.base.Predicate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Profile("!prod")
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket detailApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis((Predicate<RequestHandler>)RequestHandlerSelectors.basePackage("br.com.wishlist.controller"))
                .paths((Predicate<String>) PathSelectors.any())
                .build()
                .apiInfo(metaData());
//
    }


    private ApiInfo metaData() {

        return new ApiInfoBuilder()

        .title("Gryffindor Squad - Projeto Final")
        .description("Testando a config do Swagger")
        .version("1.0.0")
        .license("Apache License Version 2.0")
        .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
        .build();
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
