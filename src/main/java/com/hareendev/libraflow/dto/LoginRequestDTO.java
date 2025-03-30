package com.hareendev.libraflow.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequestDTO {
    private String userName;
    private String password;
}
