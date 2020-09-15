package com.transcacheservice.cacheapp.service;

import com.transcacheservice.cacheapp.security.jwt.JwtUser;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Component(value = "auditorAwareBean")
public class AuditorAwareBean implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                JwtUser jwtUser = (JwtUser) auth.getPrincipal();
                return Optional.of(jwtUser.getUsername());
            }
        }
        return Optional.of("Unknown");
    }
}