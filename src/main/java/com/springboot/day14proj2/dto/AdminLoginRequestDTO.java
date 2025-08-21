package com.springboot.day14proj2.dto;

import lombok.Data;

@Data
public class AdminLoginRequestDTO {
    private String username;
    private String password;
}
