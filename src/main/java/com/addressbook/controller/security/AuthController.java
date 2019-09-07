///*
// * ©  Implicitly86 All Rights Reserved
// */
//
//package com.addressbook.controller.security;
//
//
//import com.addressbook.dto.security.CredentialsDTO;
//import com.addressbook.dto.security.TokenDTO;
//import com.addressbook.security.JwtTokenProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//
//import static org.springframework.http.ResponseEntity.ok;
//
//@RestController
//public class AuthController {
//
//    private final AuthenticationManager authenticationManager;
//    private final JwtTokenProvider tokenProvider;
//
//    @Autowired
//    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
//        this.authenticationManager = authenticationManager;
//        this.tokenProvider = tokenProvider;
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<TokenDTO> authenticateUser(@Valid @RequestBody CredentialsDTO credentials) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword())
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = tokenProvider.generateToken(authentication);
//        return ok(TokenDTO.builder().accessToken(jwt).build());
//    }
//
//}
