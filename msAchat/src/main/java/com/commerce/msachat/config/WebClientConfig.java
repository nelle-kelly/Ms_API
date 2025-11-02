package com.commerce.msachat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration pour WebClient
 * Permet de faire des appels HTTP vers d'autres microservices
 */
@Configuration
public class WebClientConfig {

    /**
     * Bean WebClient.Builder
     * Spring va l'injecter automatiquement dans nos services
     */
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
