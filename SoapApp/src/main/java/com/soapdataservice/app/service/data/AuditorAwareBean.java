package com.soapdataservice.app.service.data;

import org.springframework.data.domain.AuditorAware;
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
        return Optional.of("current_user");
    }
}
