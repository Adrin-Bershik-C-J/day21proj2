package com.springboot.day14proj2.controller;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.day14proj2.dto.StudentResponseDTO;
import com.springboot.day14proj2.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    @Operation(summary = "Get all students", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<StudentResponseDTO>> getStudents(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Integer roomNumber,
            @RequestParam(required = false) Boolean feePaid) {

        List<StudentResponseDTO> students = studentService.getStudents(id, name, age, roomNumber, feePaid);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/all")
    @Operation(summary = "Filter students", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Page<StudentResponseDTO>> sortStudents(
            @PageableDefault(size = 5, sort = "name") Pageable pageable) {
        Page<StudentResponseDTO> students = studentService.sortStudents(pageable);
        return ResponseEntity.ok(students);
    }

}