package com.lemur.service2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/service2/admin")
public class AdminController {

    // ADMIN
    @GetMapping
    public String adminService() {
        return "Admin service";
    }

    // ADMIN
    @GetMapping("/service2")
    public String adminService2() {
        return "Admin service2";
    }
}
