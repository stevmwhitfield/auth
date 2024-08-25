package dev.stevenwhitfield.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @GetMapping("/")
    public String home() {
        return "Authentication Service";
    }

    @GetMapping("/hello")
    public String hello(Authentication auth) {
        return "Hello " + auth.getName();
    }

}
