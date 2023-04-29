package com.lemur.service2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/service2/all")
public class AllRolesController {

    // ALL ROLES
    @GetMapping
    public String helloService2() {
        return "Hello from service2!";
    }

    // ALL ROLES
    @GetMapping("/all2")
    public String allService2() {
        return "Service2 all";
    }
}
