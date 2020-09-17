package com.restservice.app.service.security;

import com.restservice.app.domain.security.Role;
import com.restservice.app.domain.security.Status;
import com.restservice.app.domain.security.User;
import com.restservice.app.exceptions.BadRequestException;
import com.restservice.app.exceptions.DatabaseUnavailableException;
import com.restservice.app.repository.security.RoleRepository;
import com.restservice.app.repository.security.UserRepository;
import com.restservice.app.dto.security.UserRegistrationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Service
public class UserServiceImp implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(UserRegistrationDto userRegistrationDto) {
        List<Role> userRoles = new ArrayList<>();
        // Get all user roles by it's names
        if(userRegistrationDto.getRoles().isEmpty()) {
            userRoles.add(roleRepository.findByName("ROLE_USER"));
        } else {
            userRegistrationDto.getRoles()
                    .forEach(role -> userRoles.add(roleRepository.findByName("ROLE_" + role.toUpperCase())));
        }
        // Cast to entity User
        User user = User.fromUserRegistrationDto(userRegistrationDto, userRoles);
        // problem with password encoding. Password should be encoded when parsing through an internet,
        // do I care about encoded password being encoded again?
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);

        try {
            User registeredUser = userRepository.save(user);
            logger.info("IN register - user: {} successfully registered", registeredUser);
            return registeredUser;
        }catch (ValidationException e) {
            throw new BadRequestException("Registration request for username " + userRegistrationDto.getUsername() +
                    "is not valid");
        } catch (RuntimeException e) {
            logger.error("IN register - exception during saving a user. Message: {}", e.getLocalizedMessage());
            throw new DatabaseUnavailableException("Sorry, can't save a user. Database unavailable right now");
        }
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        logger.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        logger.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            logger.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        logger.info("IN findById - user: {} found by id: {}", result, id);
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        logger.info("IN delete - user with id: {} successfully deleted", id);
    }
}
