package com.herbert.conferencial.service;

import com.herbert.conferencial.model.Room;
import com.herbert.conferencial.repository.RoomRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {

    @Mock
    RoomRepository roomRepository;

    @InjectMocks
    RoomService roomService;

    Room testRoom = new Room();
    Room testRoomTwo = new Room();

    List<Room> testRoomsList;


    @BeforeEach
    public void beforeEach() {
        testRoom.setName("Test room");
        testRoom.setLocation("Test street 1");
        testRoom.setMaxSeats(20);

        testRoomTwo.setName("Test room 2");
        testRoom.setLocation("Test street 2");
        testRoom.setMaxSeats(100);

        testRoomsList = List.of(testRoom, testRoomTwo);
    }

    @Test
    void getRoomsShouldReturnAListOfRooms() {
        Mockito.when(roomRepository.findAll()).thenReturn(testRoomsList);

        List<Room> listOfRooms = roomService.getRooms();

        Assertions.assertEquals(testRoomsList.size(), listOfRooms.size());
    }

    @Test
    void addNewRoomShouldAddNewRoom() {
        Mockito.when(roomRepository.save(Mockito.any())).thenReturn(testRoom);

        Room newlyAddedTestRoom = roomService.addNewRoom(testRoom);

        Assertions.assertEquals(testRoom.getLocation(), newlyAddedTestRoom.getLocation());
        Assertions.assertEquals(testRoom.getName(), newlyAddedTestRoom.getName());
        Assertions.assertEquals(testRoom.getMaxSeats(), newlyAddedTestRoom.getMaxSeats());
    }
}
