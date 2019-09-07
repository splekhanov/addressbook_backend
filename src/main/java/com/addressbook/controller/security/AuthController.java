//package com.addressbook.controller.security;
//
//import com.addressbook.dto.security.CredentialsDTO;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import springfox.documentation.annotations.ApiIgnore;
//
//import javax.validation.Valid;
//
//import static org.springframework.http.ResponseEntity.ok;
//
//@RestController
//@ApiIgnore
//public class AuthController {
//
//    private final AuthenticationManager authenticationManager;
//
//    @Autowired
//    public AuthController(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }
//
//    @PostMapping("/login")
//    public void authenticateUser(@Valid @RequestBody CredentialsDTO credentials) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword())
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
////        String jwt = tokenProvider.generateToken(authentication);
////        return ok(TokenDTO.builder().accessToken(jwt).build());
//    }
//
//}
