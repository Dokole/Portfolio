package com.transcacheservice.cacheapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Configuration
public class PasswordEncoderConfig {

    // Don't know where to put it yet. In proper places it forms a dependency cycle.
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
}
