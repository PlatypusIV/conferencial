package com.herbert.conferencial.service;

import com.herbert.conferencial.model.Room;
import com.herbert.conferencial.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    RoomRepository roomRepository;

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    public Room addNewRoom(Room newRoom){
        return roomRepository.save(newRoom);
    }
}
