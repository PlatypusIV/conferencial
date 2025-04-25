package com.herbert.conferencial.controller;

import com.herbert.conferencial.model.Room;
import com.herbert.conferencial.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class RoomController {

    RoomService roomService;
    //add room
    @PostMapping(path = "/rooms")
    private Room addRoom(@RequestBody Room room){
        return roomService.addNewRoom(room);
    }

    //remove room
    @DeleteMapping(path = "/rooms/{roomId}")
    private ResponseEntity<String> removeRoom(@PathVariable("roomId") int roomId){
        return new ResponseEntity<>("Temporary delete message", HttpStatus.NO_CONTENT);
    }

    //get all rooms
    @GetMapping(path = "/rooms")
    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
    }

    //get available rooms by time
}
