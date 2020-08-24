package com.cmed.health.core.service;

import com.cmed.health.core.dto.UserDto;
import com.cmed.health.core.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Creator : Tanvir Chowdhury
 * Date    : 2020-08-23
 */

public interface UserService<S extends UserDto> extends BaseService<User, S> {

    Optional<User> findUserByEmail(String email);

    User setPassword(String password, User user);

    User saveUser(User user);


}
