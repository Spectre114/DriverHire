package com.drive.hire.driverhire.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @PostMapping("/v1/register/")
    public ResponseEntity<String> register(@RequestParam("userId") String userId) {
        return ResponseEntity.ok("Registered user ID: " + userId);
    }

    @GetMapping(value = "/v1/login/{userId}")
    public String login(@PathVariable String userId) {
        return "login" + userId;
    }
}
