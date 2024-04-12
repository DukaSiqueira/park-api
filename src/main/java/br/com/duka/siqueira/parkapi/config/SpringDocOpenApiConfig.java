package br.com.duka.siqueira.parkapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("REST API - Park Api")
                                .description("API for parking management")
                                .version("v1")
                                .contact(new Contact().name("Eduardo Siqueira").url("https://edu-siqueira.vercel.app/"))
                );
    }

}
