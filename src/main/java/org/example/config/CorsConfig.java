package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class CorsConfig {

    @Value("${app.cors.allowed-origins:http://localhost:4200}")
    private String allowedOrigins;

    @Value("${app.cors.allowed-methods:GET,POST,PUT,DELETE,OPTIONS}")
    private String allowedMethods;

    @Value("${app.cors.allowed-headers:*}")
    private String allowedHeaders;

    @Bean
    public WebMvcConfigurer corsFilter() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        // Allow local Angular dev + your Render frontend
                        .allowedOriginPatterns(
                                "http://localhost:*",
                                "https://myshcn-family.onrender.com",  // ← YOUR frontend URL
                                "https://*.onrender.com"  // fallback for any Render frontend
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD")
                        .allowedHeaders("Content-Type", "Authorization", "X-Requested-With")
                        .exposedHeaders("Content-Disposition") // for file downloads
                        .allowCredentials(true) // required for cookies/auth (if added later)
                        .maxAge(3600); // cache preflight requests for 1 hour
            }
        };
    }
    
}

