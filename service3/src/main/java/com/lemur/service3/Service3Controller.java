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
        return "Admin service 1!";
    }

    // AMDIN
    @GetMapping("/admin2")
    public String adminService2() {
        return "Admin service 2!";
    }

    // MODERATOR
    @GetMapping("/moderator")
    public String moderatorService() {
        return "Moderator service 1";
    }

    // MODERATOR
    @GetMapping("/moderator2")
    public String moderatorService2() {
        return "Moderator service 2!";
    }

    // USER
    @GetMapping("/user")
    public String userService() {
        return "User service 1!";
    }

    // USER
    @GetMapping("/user2")
    public String userService2() {
        return "User service 2!";
    }

    // ADMIN, MODERATOR
    @GetMapping("/admin-moderator")
    public String adminModeratorService() {
        return "Admin, moderator service!";
    }

    // ADMIN, MODERATOR
    @GetMapping("/admin-moderator2")
    public String adminModeratorService2() {
        return "Admin, moderator service 2!";
    }
}
