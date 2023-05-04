package com.ecomm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity httpSecurity) throws Exception {
        logger.info("=====Inside securityWebFilterChain() of SecurityConfig=====");
        httpSecurity
                .csrf().disable()
                .authorizeExchange(exchange -> exchange.pathMatchers("/eureka/**").permitAll().anyExchange().authenticated())
                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
        return httpSecurity.build();
    }
}