package com.hareendev.jwt.controller;

import com.hareendev.jwt.service.JWTService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final JWTService jwtService;

    public HomeController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping
    public String home() {
        return "home";
    }

    @PostMapping(value = "/login")
    public String login() {
        return jwtService.getJWTToken();
    }

    @GetMapping(value = "/username")
//    public String getUsername(@RequestParam String token) {
//        return jwtService.getUsername(token);
//    }
    public String getUsername() {
        return jwtService.getUsername(jwtService.getJWTToken());
    }
}
