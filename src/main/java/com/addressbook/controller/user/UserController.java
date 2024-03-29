package com.addressbook.controller.user;

import com.addressbook.dto.security.UserDTO;
import com.addressbook.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@Api(tags = "Users", value = "UserCommands", description = "Controller for User Commands")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Register new user")
    @PostMapping("/users/registration")
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

    @ApiOperation(value = "Delete user by id", authorizations = @Authorization(value="Authorization"))
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ok().build();
    }

    @ApiOperation(value = "Undelete user by id", authorizations = @Authorization(value="Authorization"))
    @PutMapping("/users/{id}/undelete")
    public ResponseEntity<Void> undeleteUser(@PathVariable Long id) {
        userService.undeleteUser(id);
        return ok().build();
    }

    @ApiOperation(value = "Get user by id", authorizations = @Authorization(value="Authorization"))
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ok(userService.getUserById(id));
    }

    @ApiOperation(value = "Get user by name", authorizations = @Authorization(value="Authorization"))
    @GetMapping("/users")
    public ResponseEntity<UserDTO> getUserByName(@RequestParam String name) {
        return ok(userService.getUserByName(name));
    }
}
