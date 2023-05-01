package com.lemur.service1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/unauthorized")
public class UnauthorizedController {

    @GetMapping
    public String unauthorizedService() {
        return "Unauthorized service";
    }
}
