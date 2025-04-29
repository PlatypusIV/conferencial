package com.herbert.conferencial.service;

import com.herbert.conferencial.model.Room;
import com.herbert.conferencial.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    public Room addNewRoom(Room newRoom) {
        return roomRepository.save(newRoom);
    }

    public Room getRoomById(int roomId) {
        return roomRepository.findById(roomId);
    }

    public boolean deleteRoomById(long id) {
        if(roomRepository.existsById(id)){
            roomRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    //get available rooms in between timerange


}
