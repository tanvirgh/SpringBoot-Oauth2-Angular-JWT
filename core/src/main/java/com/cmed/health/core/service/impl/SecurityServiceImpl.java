package com.cmed.health.core.service.impl;


import com.cmed.health.core.entity.User;
import com.cmed.health.core.repository.UserRepository;
import com.cmed.health.core.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Creator : Tanvir Chowdhury
 * Date    : 2020-08-23
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return user.toActor();
    }
}