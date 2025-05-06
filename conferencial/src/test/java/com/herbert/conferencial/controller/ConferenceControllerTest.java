package com.herbert.conferencial.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.herbert.conferencial.model.Conference;
import com.herbert.conferencial.service.ConferenceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ConferenceController.class)
@Import(ConferenceControllerTest.TestConfig.class)
public class ConferenceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ConferenceService conferenceService;

    @Test
    @DisplayName("Should successfully add a new conference")
    void shouldAddNewConference() throws Exception {
        Conference testConference = new Conference();
        testConference.setName("TestConf");
        testConference.setRoomId(1);
        testConference.setStartTime(LocalDateTime.now().plusDays(2));
        testConference.setEndTime(LocalDateTime.now().plusDays(2).plusHours(1));
        testConference.setCanceled(false);

        when(conferenceService.addNewConference(Mockito.any())).thenReturn(testConference);

        mockMvc.perform(post("/conferences/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(testConference)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(testConference.getName()))
                .andExpect(jsonPath("$.roomId").value(testConference.getRoomId()))
                .andExpect(jsonPath("$.canceled").value(false));
    }

    @Test
    @DisplayName("Should successfully get all conferences")
    void shouldGetAllConferences() throws Exception {
        Conference testConference = new Conference();
        testConference.setName("TestConf");
        testConference.setRoomId(1);
        testConference.setStartTime(LocalDateTime.now().plusDays(2));
        testConference.setEndTime(LocalDateTime.now().plusDays(2).plusHours(1));
        testConference.setCanceled(false);

        when(conferenceService.getAllConferences()).thenReturn(List.of(testConference));

        mockMvc.perform(get("/conferences/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(testConference.getName()))
                .andExpect(jsonPath("$[0].roomId").value(testConference.getRoomId()))
                .andExpect(jsonPath("$[0].canceled").value(false));
    }

    @Test
    @DisplayName("Should successfully get conference by id")
    void shouldGetConferenceById() throws Exception {
        Conference testConference = new Conference();
        testConference.setName("TestConf");
        testConference.setRoomId(1);
        testConference.setStartTime(LocalDateTime.now().plusDays(2));
        testConference.setEndTime(LocalDateTime.now().plusDays(2).plusHours(1));
        testConference.setCanceled(false);

        when(conferenceService.findConferenceById(1)).thenReturn(testConference);

        mockMvc.perform(get("/conferences/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(testConference.getName()))
                .andExpect(jsonPath("$.roomId").value(testConference.getRoomId()))
                .andExpect(jsonPath("$.canceled").value(false));
    }

    @Test
    @DisplayName("Should successfully cancel conference with id 1")
    void shouldCancelConferenceById() throws Exception {

        mockMvc.perform(patch("/conferences/cancel/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Canceled"));
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public ConferenceService roomService() {
            return mock(ConferenceService.class);
        }
    }
}
