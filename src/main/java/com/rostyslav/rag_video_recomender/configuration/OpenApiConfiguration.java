package com.rostyslav.rag_video_recomender.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("RAG Video Recommender API")
                        .description("API for a video recommendation system using Retrieval-Augmented Generation (RAG)")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Rostyslav")
                                .url("https://github.com/rostyslavpaliuha"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}