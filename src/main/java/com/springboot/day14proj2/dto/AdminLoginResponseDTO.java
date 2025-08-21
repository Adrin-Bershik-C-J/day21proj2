package com.springboot.day14proj2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminLoginResponseDTO {
    private String jwt;
    private String userName;
}
