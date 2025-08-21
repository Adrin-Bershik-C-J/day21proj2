package com.springboot.day14proj2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.day14proj2.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
