package com.drive.hire.driverhire.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("driver/")
public class DriverController {

    @GetMapping("/v1/login/{driverId}")
    public String login(@PathVariable String driverId) {
        return "login" + driverId;
    }
}
