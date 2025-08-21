package com.springboot.day14proj2.dto;

import lombok.Data;

@Data
public class StudentResponseDTO {

    private Long id;
    private String name;
    private Integer age;
    private Boolean feePaid;
    private Integer roomNumber;
}
