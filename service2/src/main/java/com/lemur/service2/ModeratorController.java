package com.lemur.service2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/service2/moderator")
public class ModeratorController {

    // MODERATOR
    @GetMapping
    public String moderatorService() {
        return "Moderator service";
    }

    // MODERATOR
    @GetMapping("service2")
    public String moderatorService2() {
        return "Moderator service 2";
    }
}
