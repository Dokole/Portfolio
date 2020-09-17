package com.restservice.app.service.security;

import com.restservice.app.domain.security.User;
import com.restservice.app.dto.security.UserRegistrationDto;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface UserService {

    User register(UserRegistrationDto userRegistrationDto);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);
}
