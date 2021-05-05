package br.com.wishlist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;

@Profile("!prod")
@Configuration
@EnableSwagger2
public class DictSwaggerConfig {

    @Bean
    public Docket detailApi() {

        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        docket
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.wishlist.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.infoApi().build())
                .consumes(new HashSet<String>(Arrays.asList("application/json")))
                .produces(new HashSet<String>(Arrays.asList("application/json")));

        return docket;
    }


    private ApiInfoBuilder infoApi() {

        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

        apiInfoBuilder.title("Suicide Squad");
        apiInfoBuilder.description("Rest API to provide Detartament registration");
        apiInfoBuilder.version("0.0.1");
        apiInfoBuilder.license("Simple Bank Tec");
        apiInfoBuilder.licenseUrl("http://www.simplebank.com");
        apiInfoBuilder.contact(this.contact());

        return apiInfoBuilder;
    }

    private Contact contact() {

        return new Contact(
                "Simple Bank",
                "http://www.simplebank.com",
                "tec@simplebank.com");
    }
}
