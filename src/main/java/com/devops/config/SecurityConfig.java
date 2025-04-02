package com.devops.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.disable())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(fl -> fl.loginPage("/login").defaultSuccessUrl("/index", true))
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/test","/login", "/register","/registration", "/css/**", "/js/**"))
                // Allow unauthenticated access to login and register pages
                .securityMatcher("/**")
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/**").permitAll()) // All other requests require authentication
                .headers(req -> req.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()));  // Allow logout for all users
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
