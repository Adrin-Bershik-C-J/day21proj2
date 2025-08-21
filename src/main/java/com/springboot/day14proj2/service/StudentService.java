package com.springboot.day14proj2.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.day14proj2.dto.StudentResponseDTO;
import com.springboot.day14proj2.mapper.StudentMapper;
import com.springboot.day14proj2.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public List<StudentResponseDTO> getStudents(Long id, String name, Integer age, Integer roomNumber,
            Boolean feePaid) {
        return studentRepository.findAll().stream()
                .filter(student -> id == null || student.getId().equals(id))
                .filter(student -> name == null || student.getName().equalsIgnoreCase(name))
                .filter(student -> age == null || student.getAge().equals(age))
                .filter(student -> roomNumber == null ||
                        (student.getRoom() != null && student.getRoom().getRoomNumber()
                                .equals(roomNumber)))
                .filter(student -> feePaid == null || student.getFeePaid().equals(feePaid))
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<StudentResponseDTO> sortStudents(Pageable pageable) {
        return studentRepository.findAll(pageable)
                .map(studentMapper::toDto);
    }

}
