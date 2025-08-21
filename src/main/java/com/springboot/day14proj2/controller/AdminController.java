package com.springboot.day14proj2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.day14proj2.dto.StudentResponseDTO;
import com.springboot.day14proj2.entity.Student;
import com.springboot.day14proj2.service.AdminService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    @Operation(summary = "Create student", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<StudentResponseDTO> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(adminService.createStudent(student));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update student", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<StudentResponseDTO> updateStudent(@RequestBody Student student, @PathVariable Long id) {
        return ResponseEntity.ok(adminService.updateStudent(student, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete student", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        adminService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

}
