package com.drive.hire.driverhire.controller;

import com.drive.hire.driverhire.model.User;
import com.drive.hire.driverhire.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/v1/register/")
    public ResponseEntity<String> register(@RequestBody User user) {

        try {
            String response = userService.registerUser(user);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/v1/login/{userId}")
    public String login(@PathVariable String userId) {
        return "login" + userId;
    }
}
