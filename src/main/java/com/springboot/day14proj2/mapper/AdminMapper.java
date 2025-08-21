package com.springboot.day14proj2.mapper;

import org.mapstruct.Mapper;

import com.springboot.day14proj2.dto.AdminLoginRequestDTO;
import com.springboot.day14proj2.dto.AdminLoginResponseDTO;
import com.springboot.day14proj2.entity.Admin;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    Admin toEntity(AdminLoginRequestDTO requestDTO);

    AdminLoginResponseDTO toDto(Admin warden);
}
