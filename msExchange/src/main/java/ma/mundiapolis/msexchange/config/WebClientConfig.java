package ma.mundiapolis.msexchange.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {

    /**
      Bean WebClient.Builder
      Pour que Spring l'injecte automatiquement dans nos services
     */

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
