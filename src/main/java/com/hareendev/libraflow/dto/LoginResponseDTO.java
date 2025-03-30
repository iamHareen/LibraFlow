package com.hareendev.libraflow.dto;

import lombok.Data;

import java.util.Set;

@Data
public class LoginResponseDTO {
    private String token;
    private String userName;
    private Set<String> roles;
}
