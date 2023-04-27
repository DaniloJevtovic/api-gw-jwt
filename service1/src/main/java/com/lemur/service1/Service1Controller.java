package com.lemur.service1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/service1")
public class Service1Controller {

    // ADMIN, MODERATOR, USER
    @GetMapping
    public String helloService1() {
        return "Hello from service1!";
    }
}
