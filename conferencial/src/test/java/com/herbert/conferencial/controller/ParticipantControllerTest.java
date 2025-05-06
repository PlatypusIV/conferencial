package com.herbert.conferencial.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.herbert.conferencial.model.Participant;
import com.herbert.conferencial.service.ParticipantService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParticipantController.class)
@Import(ParticipantControllerTest.TestConfig.class)
public class ParticipantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ParticipantService participantService;

    @Test
    @DisplayName("Should successfully add a new participant")
    void shouldAddNewParticipant() throws Exception {
        Participant testParticipant = new Participant();
        testParticipant.setFullName("Test1");
        testParticipant.setBirthDate(LocalDate.of(2000, 1, 1));
        testParticipant.setConferenceId(1);

        when(participantService.addNewParticipant(Mockito.any())).thenReturn(testParticipant);

        mockMvc.perform(post("/participants/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(testParticipant)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fullName").value(testParticipant.getFullName()));
    }

    @Test
    @DisplayName("Should get all participants for conference")
    void shouldGetAllParticipantsForConference() throws Exception {
        Participant testParticipant = new Participant();
        testParticipant.setFullName("Test1");
        testParticipant.setBirthDate(LocalDate.of(2000, 1, 1));
        testParticipant.setConferenceId(1);

        when(participantService.getParticipantsRelatedToConference(1)).thenReturn(List.of(testParticipant));

        mockMvc.perform(get("/participants/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fullName").value(testParticipant.getFullName()))
                .andExpect(jsonPath("$[0].conferenceId").value(testParticipant.getConferenceId()));
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public ParticipantService roomService() {
            return mock(ParticipantService.class);
        }
    }
}
