package com.herbert.conferencial.controller;

import com.herbert.conferencial.errorHandling.GeneralException;
import com.herbert.conferencial.errorHandling.GlobalExceptionHandler;
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
@CrossOrigin
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping(path = "/")
    public ResponseEntity<Room> addNewRoom(@RequestBody Room room) {
        Room createdRoom = roomService.addNewRoom(room);
        return ResponseEntity.ok(createdRoom);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<Room>> getAllRooms(Pageable page) {
        List<Room> rooms = roomService.getRooms();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> getRoomById(@PathVariable("id") int id) {
        Room room = roomService.getRoomById(id);
        if(room == null) return new ResponseEntity<>("Room not found", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(room);
    }
}
