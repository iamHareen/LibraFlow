package com.hareendev.libraflow.controller;

import com.hareendev.libraflow.dto.LoginRequestDTO;
import com.hareendev.libraflow.dto.LoginResponseDTO;
import com.hareendev.libraflow.dto.RegisterRequestDTO;
import com.hareendev.libraflow.model.User;
import com.hareendev.libraflow.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticateService authenticateService;

    @PostMapping("/registernormaluser")
    public ResponseEntity<User> registerNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.ok(authenticateService.registerNormalUser(registerRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authenticateService.login(loginRequestDTO));
    }

}
