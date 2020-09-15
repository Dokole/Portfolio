package com.transcacheservice.cacheapp.repository.security;

import com.transcacheservice.cacheapp.domain.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u " +
            "where u.status = 'ACTIVE'")
    List<User> getAll();
    @Query("select u from User u " +
            "where u.id = :id and u.status = 'ACTIVE'")
    Optional<User> findById(@Param("id") Long id);

    @Query("update User set status = 'DELETED'")
    void delete(Long id);

    @Query("select u from User u " +
            "where u.username = :username and u.status = 'ACTIVE'")
    User findByUsername(@Param("username") String name);
}
