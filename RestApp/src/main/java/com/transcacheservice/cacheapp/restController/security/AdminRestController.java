package com.transcacheservice.cacheapp.restController.security;

import com.transcacheservice.cacheapp.dto.security.AdminUserDto;
import com.transcacheservice.cacheapp.domain.security.User;
import com.transcacheservice.cacheapp.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminRestController {

    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = AdminUserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/users/{id}")
    public void deleteUserById(@PathVariable(name = "id") Long id) {
        userService.delete(id);
    }


}
