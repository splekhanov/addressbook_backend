package com.addressbook.controller.user;

import com.addressbook.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.noContent;

@RestController
@Api(tags = "User", value = "UserCommands", description = "Controller for User Commands")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Delete user by name", authorizations = @Authorization(value="Authorization"))
    @DeleteMapping("/user/{name}")
    public ResponseEntity<Void> deleteUser(@PathVariable String name) {
        userService.deleteUserByUserName(name);
        return noContent().build();
    }
}
