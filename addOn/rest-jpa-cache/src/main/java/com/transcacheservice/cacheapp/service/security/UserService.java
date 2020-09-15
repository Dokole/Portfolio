package com.transcacheservice.cacheapp.service.security;

import com.transcacheservice.cacheapp.dto.security.UserRegistrationDto;
import com.transcacheservice.cacheapp.domain.security.User;

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
