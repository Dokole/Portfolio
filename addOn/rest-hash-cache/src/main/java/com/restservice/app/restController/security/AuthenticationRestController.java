package com.restservice.app.restController.security;

import com.restservice.app.dto.security.AuthenticationRequestDto;
import com.restservice.app.dto.security.UserDto;
import com.restservice.app.dto.security.UserRegistrationDto;
import com.restservice.app.exceptions.BadRequestException;
import com.restservice.app.domain.security.User;
import com.restservice.app.security.jwt.JwtTokenProvider;
import com.restservice.app.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<Object, Object>> login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));

            User user = userService.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/registration")
    public UserDto registration(@RequestBody @Valid UserRegistrationDto userRegistrationDto, Errors errors) {
        if(errors.hasErrors()) {
            throw new BadRequestException("Registration request for username " + userRegistrationDto.getUsername() +
                    " is not valid");
        }
        User user = userService.register(userRegistrationDto);
        return UserDto.fromUser(user);
    }
}
