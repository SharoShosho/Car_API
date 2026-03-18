// OpenApiConfig.java
package org.example.biluthyrning.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI biluthyrningOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Biluthyrning API")
                        .description("REST API för hantering av biluthyrning - Inlämningsuppgift\n\n" +
                                "## API Översikt\n" +
                                "Detta API tillhandahåller funktionalitet för att hantera:\n" +
                                "- **Bilar**: CRUD-operationer för bilarna i uthyrningssystemet\n" +
                                "- **Kunder**: Hantering av kundinformation\n" +
                                "- **Uthyrningar**: Process för att hyra ut bilar till kunder\n\n" +
                                "## Teknisk Information\n" +
                                "- **Spring Boot**: 3.2.0\n" +
                                "- **Java**: 17\n" +
                                "- **Database**: MySQL\n" +
                                "- **Format**: JSON")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("API Support")
                                .email("support@biluthyrning.se")
                                .url("https://biluthyrning.se"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Lokal utvecklingsserver")
                ));
    }
}