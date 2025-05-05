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
        return (roomService.deleteRoomById(roomId))
                ? new ResponseEntity<>("Deleted successfully", HttpStatus.NO_CONTENT)
                : new ResponseEntity<>("Room not found", HttpStatus.NOT_FOUND);
    }

    //get all rooms
    @GetMapping(path = "/")
    public ResponseEntity<List<Room>> getAllRooms(Pageable page) {
        List<Room> rooms = roomService.getRooms();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable("id") int id) {
        Room room = roomService.getRoomById(id);
        return ResponseEntity.ok(room);
    }
}
