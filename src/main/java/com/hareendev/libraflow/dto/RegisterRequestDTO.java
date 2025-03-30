package com.hareendev.libraflow.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequestDTO {
    private String userName;
    private String email;
    private String password;
}
