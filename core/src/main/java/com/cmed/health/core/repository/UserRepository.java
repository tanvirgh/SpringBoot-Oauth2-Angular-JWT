package com.cmed.health.core.repository;

import com.cmed.health.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Creator : Tanvir Chowdhury
 * Date    : 2020-08-23
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

}
