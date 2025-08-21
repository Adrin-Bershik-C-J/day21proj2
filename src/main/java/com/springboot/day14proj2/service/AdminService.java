package com.springboot.day14proj2.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.springboot.day14proj2.dto.StudentResponseDTO;
import com.springboot.day14proj2.dto.AdminLoginRequestDTO;
import com.springboot.day14proj2.dto.AdminLoginResponseDTO;
import com.springboot.day14proj2.entity.Room;
import com.springboot.day14proj2.entity.Student;
import com.springboot.day14proj2.mapper.StudentMapper;
import com.springboot.day14proj2.repository.RoomRepository;
import com.springboot.day14proj2.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RoomRepository roomRepository;

    public StudentResponseDTO createStudent(Student student) {
        // find room by roomNumber from request
        Integer roomNumber = student.getRoom().getRoomNumber();

        Room room = roomRepository.findByRoomNumber(roomNumber)
                .orElseThrow(() -> new RuntimeException("Room not found with number " + roomNumber));

        student.setRoom(room); // assign actual persisted room
        Student saved = studentRepository.save(student);

        return studentMapper.toDto(saved);
    }

    public StudentResponseDTO updateStudent(Student student, Long id) {
        Student s = studentRepository.findById(id).map(existingStudent -> {
            existingStudent.setAge(student.getAge());
            existingStudent.setName(student.getName());
            existingStudent.setFeePaid(student.getFeePaid());

            // resolve room by roomNumber (same approach as create)
            if (student.getRoom() != null && student.getRoom().getRoomNumber() != null) {
                Room room = roomRepository.findByRoomNumber(student.getRoom().getRoomNumber())
                        .orElseThrow(() -> new RuntimeException("Room not found with number "
                                + student.getRoom().getRoomNumber()));
                existingStudent.setRoom(room);
            }

            return studentRepository.save(existingStudent);
        }).orElseThrow(() -> new RuntimeException("Student not found with id " + id));

        return studentMapper.toDto(s);
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id " + id);
        }
        studentRepository.deleteById(id);
    }

    public AdminLoginResponseDTO login(AdminLoginRequestDTO user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(userDetails.getUsername());

        return new AdminLoginResponseDTO(token, userDetails.getUsername());
    }

}
