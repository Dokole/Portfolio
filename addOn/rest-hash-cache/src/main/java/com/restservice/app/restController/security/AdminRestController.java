package com.restservice.app.restController.security;

import com.restservice.app.dto.security.AdminUserDto;
import com.restservice.app.domain.security.User;
import com.restservice.app.service.cacheService.ItemCacheService;
import com.restservice.app.service.security.UserService;
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
    private final ItemCacheService itemDatabaseService;

    @Autowired
    public AdminRestController(UserService userService, ItemCacheService itemDatabaseService) {
        this.userService = userService;
        this.itemDatabaseService = itemDatabaseService;
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/database/invalidateCaches")
    public String invalidateCaches() {
        itemDatabaseService.invalidateRelatedCaches();
        itemDatabaseService.invalidateRelatedCachesDelay();
        return "Caches invalidated";
    }


}
