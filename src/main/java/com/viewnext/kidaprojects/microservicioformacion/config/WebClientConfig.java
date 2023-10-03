package com.viewnext.kidaprojects.microservicioformacion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * La clase {@code WebClientConfig} proporciona la configuración para crear instancias de WebClient
 * que se utilizarán para realizar solicitudes HTTP a un servidor remoto.
 *
 * <p>El autor de esta clase es Víctor Colorado "Kid A".</p>
 *
 * @version 1.0
 * @since 3 de octubre de 2023
 */
@Configuration
public class WebClientConfig {

    private static final String BASE_URL = "http://localhost:8080";

    /**
     * Crea y configura un WebClient para interactuar con un servidor en la URL base especificada.
     *
     * @return Una instancia de WebClient configurada para la URL base.
     */
    @Bean
    WebClient cursoWebClient() {
        String apiUrl = BASE_URL;
        return WebClient.create(apiUrl);
    }
}
