package com.herbert.conferencial.repository;


import com.herbert.conferencial.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAll();
    Room findById(int id);
}
