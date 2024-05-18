package com.mybot.labs.javabot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.mybot.labs.javabot.model.UserAuthority;

@Slf4j
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(expressionInterceptUrlRegistry ->
                        expressionInterceptUrlRegistry
                                .requestMatchers(HttpMethod.POST, "/registration").permitAll()
                                .requestMatchers(HttpMethod.GET, "/login").permitAll()
                                .requestMatchers(HttpMethod.GET, "/jokes", "/jokes/*").permitAll()
                                .requestMatchers(HttpMethod.POST, "/jokes").hasAuthority(UserAuthority.PLACE_ORDERS.getAuthority())
                                .requestMatchers(HttpMethod.PUT, "/jokes/**").hasAuthority(UserAuthority.MANAGE_ORDERS.getAuthority())
                                .requestMatchers(HttpMethod.DELETE, "/jokes/**").hasAuthority(UserAuthority.MANAGE_ORDERS.getAuthority())
                                .requestMatchers("/users/**", "/admin/**").hasAuthority(UserAuthority.FULL.getAuthority())
                                .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
