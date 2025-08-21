package com.springboot.day14proj2.repository;

import com.springboot.day14proj2.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByRoomNumber(Integer roomNumber);
}
