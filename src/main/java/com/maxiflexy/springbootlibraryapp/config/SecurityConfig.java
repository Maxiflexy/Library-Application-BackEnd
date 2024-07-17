package com.maxiflexy.springbootlibraryapp.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.List;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        //Disable cross site request forgery
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        // Add CORS filter
        httpSecurity.cors(cors -> cors
                .configurationSource(corsConfigurationSource()));

        // Configure JWT-based OAuth2 resource server
        httpSecurity
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwkSetUri("https://dev-61814843.okta.com/oauth2/default/v1/keys")
                        )
                );

        // Protect endpoints at /api/<type>/secure
        httpSecurity.authorizeHttpRequests(configurer -> configurer.
                requestMatchers(
                        antMatcher(HttpMethod.valueOf("/api/books/secure/**")),
                        antMatcher(HttpMethod.valueOf("/api/reviews/secure/**")),
                        antMatcher(HttpMethod.valueOf("/api/messages/secure/**")),
                        antMatcher(HttpMethod.valueOf("/api/admin/secure/**"))
                ).authenticated()
                .anyRequest().permitAll());


        // Add content negotiation strategy
        httpSecurity.setSharedObject(ContentNegotiationStrategy.class, new HeaderContentNegotiationStrategy());

        // Force a non-empty response body for 401's to make the response friendly
        Okta.configureResourceServer401ResponseBody(httpSecurity);

        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000")); // Replace with allowed origins if needed
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*")); // Replace with allowed headers if needed
        configuration.setAllowCredentials(true); // Set to true if credentials are allowed
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
