package io.acme.longlife.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean(name = "longLifeOpenApi")
    public OpenAPI longLifeOpenApi() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("LongLife Application API")
                        .description(
                                ""));
    }
}
