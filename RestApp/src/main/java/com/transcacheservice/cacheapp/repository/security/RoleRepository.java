package com.transcacheservice.cacheapp.repository.security;

import com.transcacheservice.cacheapp.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
