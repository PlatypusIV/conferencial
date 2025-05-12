package com.herbert.conferencial.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.herbert.conferencial.model.Room;
import com.herbert.conferencial.service.RoomService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoomController.class)
@Import(RoomControllerTest.TestConfig.class)
public class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RoomService roomService;

    @Test
    @DisplayName("Should successfully add a new room")
    void shouldAddNewRoom() throws Exception {
        Room room = new Room();
        room.setName("Test Room");
        room.setLocation("Test Location");
        room.setMaxSeats(20);

        when(roomService.addNewRoom(any(Room.class))).thenReturn(room);

        mockMvc.perform(post("/rooms")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(room)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Room"))
                .andExpect(jsonPath("$.location").value("Test Location"))
                .andExpect(jsonPath("$.maxSeats").value(20));
    }

    @Test
    @DisplayName("Should return 404 because room was not found")
    void shouldReturnNotFoundWhenRoomDoesNotExist() throws Exception {
        int roomId = 999;

        mockMvc.perform(get("/rooms/{roomId}", roomId))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Room not found"));
    }

    @Test
    @DisplayName("Should return a list of Rooms")
    void shouldReturnAListOfRooms() throws Exception {
        Room testRoom = new Room();
        testRoom.setName("Test room 1");

        when(roomService.getRooms()).thenReturn(List.of(testRoom));

        mockMvc.perform(get("/rooms"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(testRoom.getName()));
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public RoomService roomService() {
            return mock(RoomService.class);
        }
    }
}