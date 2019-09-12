package com.addressbook.service.impl;

import com.addressbook.entity.security.User;
import com.addressbook.repository.security.UserRepository;
import com.addressbook.security.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("UserDTO not found with username or email : " + username);
        }
        return UserPrincipal.create(user.orElse(null));
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("UserDTO not found with id : " + id);
        }
        return UserPrincipal.create(user);
    }
}
