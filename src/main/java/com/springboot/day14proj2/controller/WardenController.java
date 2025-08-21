package com.springboot.day14proj2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.day14proj2.dto.StudentResponseDTO;
import com.springboot.day14proj2.entity.Student;
import com.springboot.day14proj2.service.WardenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/warden")
@RequiredArgsConstructor
public class WardenController {
    private final WardenService wardenService;

    @PatchMapping("/allocate/{id}")
    @Operation(summary = "Allocate room for student", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<StudentResponseDTO> allocateRoom(@RequestBody Student student, @PathVariable Long id) {
        return ResponseEntity.ok(wardenService.allocateRoom(student, id));
    }
}
