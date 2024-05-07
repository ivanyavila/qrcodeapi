package com.aldebaran.qrcodeapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title="QR Code API",
                version="0.1-Beta",
                description = "QR Code API manager"
        )
)
public class SwaggerConfig {
}
