package com.transcacheservice.cacheapp.security;

import com.transcacheservice.cacheapp.domain.security.User;
import com.transcacheservice.cacheapp.security.jwt.JwtUser;
import com.transcacheservice.cacheapp.security.jwt.JwtUserFactory;
import com.transcacheservice.cacheapp.service.security.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Service("userDetailsService")
public class JwtUserDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        logger.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
        return jwtUser;
    }
}
