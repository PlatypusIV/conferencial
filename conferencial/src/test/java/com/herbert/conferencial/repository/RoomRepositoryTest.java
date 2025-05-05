package com.herbert.conferencial.repository;

import com.herbert.conferencial.model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RoomRepositoryTest {

    @Mock
    private RoomRepository roomRepository;

    private Room testRoom;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testRoom = new Room(1, "Test Hall 1", "Test street 1", 20);
        List<Room> listOfTestRooms = List.of(testRoom);

        when(roomRepository.findAll()).thenReturn(listOfTestRooms);
        when(roomRepository.findById(1)).thenReturn(testRoom);
    }

    @Test
    @DisplayName("Should save and retrieve a room by ID")
    void shouldSaveAndFindById() {
        Room foundRoom = roomRepository.findById(1);
        assertNotNull(foundRoom);
        assertEquals(foundRoom.getName(),testRoom.getName());
        assertEquals(foundRoom.getMaxSeats(), testRoom.getMaxSeats());
    }

    @Test
    @DisplayName("Should return all rooms")
    void shouldFindAllRooms() {
         List<Room> rooms = roomRepository.findAll();

        assertEquals(rooms.size(),1);
    }
}