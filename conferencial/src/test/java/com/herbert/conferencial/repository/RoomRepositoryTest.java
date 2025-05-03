package com.herbert.conferencial.repository;

import com.herbert.conferencial.model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    void testFindAll() {
        List<Room> allRooms = roomRepository.findAll();
        assertEquals(1, allRooms.size());
        assertTrue(allRooms.contains(testRoom));
    }

    @Test
    void testFindById() {
        Room foundRoom = roomRepository.findById(1);
        assertEquals(testRoom.getName(), foundRoom.getName());
    }
}