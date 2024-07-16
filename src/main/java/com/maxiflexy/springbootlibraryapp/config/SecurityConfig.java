package com.maxiflexy.springbootlibraryapp.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
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

        httpSecurity// Configure JWT-based OAuth2 resource server
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwkSetUri("https://dev-61814843.okta.com/oauth2/default/v1/keys") // replace with your Okta JWK set URI
                        )
                );

        // Protect endpoints at /api/<type>/secure
        httpSecurity.authorizeHttpRequests(configurer -> configurer
                .requestMatchers(antMatcher(HttpMethod.valueOf("/api/books/secure/**"), "/api/reviews/secure/**"))
                .authenticated()
                .anyRequest().permitAll());

//        // Configure JWT-based OAuth2 resource server
//        httpSecurity.securityMatcher(request -> {
//                    // Check if request has a JWT authentication token
//                    if(request.getUserPrincipal() instanceof JwtAuthenticationToken) {
//                        return true; // Allow request if it has a JWT token
//                    }
//                    return false; // Deny request if no JWT token is present
//                })
//                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());


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
