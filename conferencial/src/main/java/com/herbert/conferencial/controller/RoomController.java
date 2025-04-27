package com.herbert.conferencial.controller;

import com.herbert.conferencial.model.Room;
import com.herbert.conferencial.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    //add room
    @PostMapping(path = "/")
    private Room addNewRoom(@RequestBody Room room) {
        return roomService.addNewRoom(room);
    }

    //remove room
    @DeleteMapping(path = "/{roomId}")
    private ResponseEntity<String> removeRoom(@PathVariable("roomId") int roomId) {
        return new ResponseEntity<>("Temporary delete message", HttpStatus.NO_CONTENT);
    }

    //get all rooms
    @GetMapping(path = "/")
    public ResponseEntity<Page<Room>> getAllRooms(Pageable page) {
        Page<Room> rooms = roomService.getRooms(page);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping(path = "{roomId}")
    public ResponseEntity<Room> getRoomById(@PathVariable("roomId") int roomId) {
        Room room = roomService.getRoomById(roomId);
        return ResponseEntity.ok(room);
    }

    //get available rooms by time
}
