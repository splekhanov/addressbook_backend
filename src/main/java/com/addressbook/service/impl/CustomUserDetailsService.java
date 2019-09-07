///*
// * ©  Implicitly86 All Rights Reserved
// */
//
//package com.addressbook.service.impl;
//
//import com.addressbook.entity.security.User;
//import com.addressbook.repository.security.UserRepository;
//import com.addressbook.security.UserPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByName(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("UserDTO not found with username or email : " + username);
//        }
//        return UserPrincipal.create(user);
//    }
//
//    @Transactional
//    public UserDetails loadUserById(Long id) {
//        User user = userRepository.findById(id).orElse(null);
//        if (user == null) {
//            throw new UsernameNotFoundException("UserDTO not found with id : " + id);
//        }
//        return UserPrincipal.create(user);
//    }
//}
