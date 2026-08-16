package com.taskinator5000_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI taskinatorOpenAPI(){
        return new OpenAPI().info(new Info().title("Taskinator 5000 API").description("API REST para gerenciamento de tarefas pessoais, " +
                "com suporte a categorias, prioridades, vencimentos " +
                "e lembretes por e-mail.").version("1.0.0").contact(new Contact().name("Gabriel Rocha")));
    }
}
