package com.cmed.health.core.service.impl;

import com.cmed.health.core.dto.UserDto;
import com.cmed.health.core.entity.User;
import com.cmed.health.core.repository.UserRepository;
import com.cmed.health.core.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Creator : Tanvir Chowdhury
 * Date    : 2020-08-23
 */

@Log4j2
@Service
public class UserServiceImpl<S extends UserDto> implements UserService<S> {


    private ModelMapper mapper;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(ModelMapper mapper, UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public Optional<S> findById(Long id, Class<S> dtoClass) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return Optional.of(mapper.map(userOptional.get(), dtoClass));
        }
        return Optional.empty();
    }

    @Override
    public Collection<S> getAll(Class<S> dtoClass) {
        Collection<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return Collections.emptyList();
        }
        return users.stream().map(user -> mapper.map(user, dtoClass))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<S> persist(S dto) {
        return Optional.empty();
    }

    @Override
    public Optional<S> update(Long id, S dto) {
        return Optional.empty();
    }


    @Override
    public Optional<User> findUserByEmail(String email) {
        User user =  userRepository.findByEmail(email);
        return Optional.ofNullable(user);
    }

    @Override
    public User saveUser(User user){
        return  userRepository.save(user);
    }



    @Override
    public User setPassword(String password,  User user){
        user.setPassword(passwordEncoder.encode(password));
        return user;
    }



}
