package com.eon.springbootdatamanagement.configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@SecurityScheme(name = "bearerAuth", description = "JWT Bearer token", scheme = "bearer",
        type = SecuritySchemeType.HTTP, bearerFormat = "JWT", in = SecuritySchemeIn.HEADER)
//@ConditionalOnClass(OpenAPI.class)
public class SwaggerConfig {

    @Value("${server.url}")
    private String serverURL;

    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact();
        contact.setName("Swift technology");
        contact.setEmail("support@swifttech.com");
        contact.setUrl("https://swifttech.com");

        return new OpenAPI().info(new Info().title("Platform API")
                        .description("DataManagement Project")
                        .contact(contact))
                .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("bearerAuth")) // Add security globally
                .servers(List.of(new Server().url(serverURL)));
    }

    @Bean
    public OpenApiCustomizer addHeadersGlobal() {
        return openApi -> openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> operation.addParametersItem(new HeaderParameter()
                .name("X-XSRF-TOKEN").description("A custom header for all requests")
                .required(true).schema(new StringSchema()._default("BquLOJXXt2ng415MpvK4a8F0CF/w/1iawsnFqHzPGeo=")))));
    }
}
