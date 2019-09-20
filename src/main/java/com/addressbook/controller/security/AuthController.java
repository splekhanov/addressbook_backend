package com.addressbook.controller.security;

import com.addressbook.dto.security.CredentialsDTO;

import com.addressbook.dto.security.TokenDTO;
import com.addressbook.dto.security.UserDTO;
import com.addressbook.security.JwtTokenProvider;
import com.addressbook.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import java.net.URI;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@Api(tags = "Auth", value = "AuthenticationCommands", description = "Controller for Authentication Commands")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenProvider tokenProvider,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @ApiOperation(value = "Register new user")
    @PostMapping("/registration")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody UserDTO user) {
        userService.createUser(user);
        UserDTO userDTO = userService.getUserByName(user.getName());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userDTO.getId())
                .toUri();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Authorize user")
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> authenticateUser(@Valid @RequestBody CredentialsDTO credentials) {
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword())
            );
            if(authentication.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            String jwt = tokenProvider.generateToken(authentication);
            return ok(TokenDTO.builder().accessToken(jwt).build());
        } catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid email/password");
        }
    }
}
