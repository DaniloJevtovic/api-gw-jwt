package com.lemur.service2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/service2/admin-moderator")
public class AdminModeratorController {

    // ADMIN, MODERATOR
    @GetMapping
    public String service1() {
        return "admin, moderator service 1";
    }

    // ADMIN, MODERATOR
    @GetMapping("/service2")
    public String service2() {
        return "admin, moderator service 2";
    }
}
