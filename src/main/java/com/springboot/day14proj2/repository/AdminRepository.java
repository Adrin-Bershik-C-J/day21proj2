package com.springboot.day14proj2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.day14proj2.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
