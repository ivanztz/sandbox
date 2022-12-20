package com.iz.sandbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
@Profile("local")
public class ApplicationNoSecurity {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/**");
    }
}