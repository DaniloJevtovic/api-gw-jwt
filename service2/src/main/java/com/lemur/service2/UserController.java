package com.lemur.service2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/service2/user")
public class UserController {

    // USER
    @GetMapping
    public String userService() {
        return "User service";
    }
}