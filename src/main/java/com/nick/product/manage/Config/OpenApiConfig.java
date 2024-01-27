package com.nick.product.manage.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Nick Naitik",
                        email = "naitikyadav110@gmail.com",
                        url = "https://in.linkedin.com/in/naitik-yadav-84bb971a3"
                ),
                description = "Open API documentation for Inventory & Sales Management Application",
                title = "Manage Application Backend API",
                version = "1.0.0",
                license = @License(
                        name = "Naitik Licence",
                        url = "https://in.linkedin.com/in/naitik-yadav-84bb971a3"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url ="http://localhost:8080"
                ),
                @Server(
                        description = "Prod ENV",
                        url = "https://in.linkedin.com"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
