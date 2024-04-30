package com.JuanHDSM.Vendas.config.infra.documentation;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        servers = {
                @Server (
                        url = "http://localhost:8080",
                        description = "Dev"
                ),
                @Server(
                        url = "https://sales-system-course-production.up.railway.app",
                        description = "Prod"
                )
        }
)
public class SwaggerConfig {

    @Bean
    public OpenAPI customAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("sales system API")
                                .version("1.0.0")
                                .license(new License().name("Software System").url("www.teste.com")))
                .externalDocs(
                        new ExternalDocumentation()
                                .description("sales system API DOCS")
                                .url("www.testeexteraldos.com"))
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        "apiKey",
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .in(SecurityScheme.In.HEADER)
                                                .bearerFormat("JWT")
                                )
                );
    }



}
