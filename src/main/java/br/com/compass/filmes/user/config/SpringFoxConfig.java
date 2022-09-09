package br.com.compass.filmes.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;


@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("br.com.compass.filmes.user.controller"))
                    .paths(PathSelectors.regex("/api.*"))
                    .build()
                .useDefaultResponseMessages(false)
                .securitySchemes(Arrays.asList(apiKey()))
                .securityContexts(Arrays.asList(securityContext()))
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Api user")
                .description("Api to sign up a user, search movie, allocation a movie and view allocation history")
                .version("1.0.0")
                .contact(new Contact("Team Spring Beta", "https://github.com/Team-Spring-Alpha", "team.spring.compass.alpha@gmail.com"))
                .license("MIT license")
                .licenseUrl("https://spdx.org/licenses/MIT.html")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer <jwt>", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(new SecurityReference("Bearer", scopes())))
                .forPaths(PathSelectors.regex("/api/movie-payment"))
                .build();
    }

    private AuthorizationScope[] scopes() {
        AuthorizationScope[] scopes = {
                new AuthorizationScope("read", "for read operations"),
                new AuthorizationScope("write", "for write operations") };
        return scopes;
    }

}
