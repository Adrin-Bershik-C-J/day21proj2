package com.springboot.day14proj2.service;

import org.springframework.stereotype.Service;

import com.springboot.day14proj2.dto.StudentResponseDTO;
import com.springboot.day14proj2.entity.Room;
import com.springboot.day14proj2.entity.Student;
import com.springboot.day14proj2.mapper.StudentMapper;
import com.springboot.day14proj2.repository.RoomRepository;
import com.springboot.day14proj2.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WardenService {
    private final RoomRepository roomRepository;
    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    public StudentResponseDTO allocateRoom(Student student, Long id) {
        Student s = studentRepository.findById(id).map(existingStudent -> {
            // find room by roomNumber
            Room room = roomRepository.findByRoomNumber(student.getRoom().getRoomNumber())
                    .orElseThrow(() -> new RuntimeException("Room not found with number "
                            + student.getRoom().getRoomNumber()));

            existingStudent.setRoom(room); // assign new room
            return studentRepository.save(existingStudent);
        }).orElseThrow(() -> new RuntimeException("Student not found with id " + id));

        return studentMapper.toDto(s);
    }
}
