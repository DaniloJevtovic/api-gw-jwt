package com.lemur.service3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/service3")
public class Service3Controller {

    // ALL
    @GetMapping
    public String service3() {
        return "Hello from service 3!";
    }

    // AMDIN
    @GetMapping("/admin")
    public String adminService() {
        return "Hello admin!";
    }

    // MODERATOR
    @GetMapping("/moderator")
    public String moderatorService() {
        return "Hello moderator!";
    }

    // USER
    @GetMapping("/user")
    public String userService() {
        return "Hello user!";
    }
}
