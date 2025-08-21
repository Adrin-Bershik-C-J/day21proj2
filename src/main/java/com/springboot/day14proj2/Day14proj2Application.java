package com.springboot.day14proj2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springboot.day14proj2.entity.Room;
import com.springboot.day14proj2.entity.Student;
import com.springboot.day14proj2.entity.Admin;
import com.springboot.day14proj2.repository.RoomRepository;
import com.springboot.day14proj2.repository.StudentRepository;
import com.springboot.day14proj2.repository.AdminRepository;

import java.util.Arrays;

@SpringBootApplication
public class Day14proj2Application {

    public static void main(String[] args) {
        SpringApplication.run(Day14proj2Application.class, args);
    }

    @Bean
    public CommandLineRunner loadData(
            AdminRepository wardenRepository,
            RoomRepository roomRepository,
            StudentRepository studentRepository) {
        return args -> {
            // Create Wardens
            Admin warden1 = new Admin(null, "John Smith", null);
            Admin warden2 = new Admin(null, "Emma Davis", null);
            wardenRepository.saveAll(Arrays.asList(warden1, warden2));

            // Create Rooms assigned to wardens
            Room room101 = new Room(null, 101, null, warden1);
            Room room102 = new Room(null, 102, null, warden1);
            Room room201 = new Room(null, 201, null, warden2);
            roomRepository.saveAll(Arrays.asList(room101, room102, room201));

            // Create Students assigned to rooms
            Student s1 = new Student(null, "Adrin", 21, true, room101);
            Student s2 = new Student(null, "Bershik", 22, false, room101);
            Student s3 = new Student(null, "Kane", 21, true, room102);
            Student s4 = new Student(null, "Alice", 20, true, room201);
            Student s5 = new Student(null, "Williamson", 21, true, room101);
            Student s6 = new Student(null, "Shami", 22, false, room101);
            Student s7 = new Student(null, "Joe", 21, true, room102);
            Student s8 = new Student(null, "Rohit", 20, true, room201);
            Student s9 = new Student(null, "Virat", 21, true, room102);
            Student s10 = new Student(null, "Jadeja", 20, true, room201);
            studentRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10));
        };
    }
}
