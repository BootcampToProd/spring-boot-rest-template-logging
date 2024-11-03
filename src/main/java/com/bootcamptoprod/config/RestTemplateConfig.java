package com.bootcamptoprod.config;

import com.bootcamptoprod.interceptor.LoggingInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class RestTemplateConfig {

    private final LoggingInterceptor loggingInterceptor;  // Dependency injection of the LoggingInterceptor

    public RestTemplateConfig(LoggingInterceptor loggingInterceptor) {
        this.loggingInterceptor = loggingInterceptor;  // Assigning the injected interceptor
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Build a RestTemplate with the logging interceptor
        return builder
                .additionalInterceptors(Collections.singletonList(loggingInterceptor)) // Add the interceptor
                .build();  // Build and return the RestTemplate instance
    }
}
