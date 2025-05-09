package com.herbert.conferencial.service;

import com.herbert.conferencial.errorHandling.GeneralException;
import com.herbert.conferencial.model.Conference;
import com.herbert.conferencial.repository.ConferenceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConferenceServiceTest {


    @Mock
    private ConferenceRepository conferenceRepository;

    @InjectMocks
    private ConferenceService conferenceService;

    private Conference testConference;

    @BeforeEach
    void beforeEach() {
        testConference = new Conference();
        testConference.setName("TestConf");
        testConference.setRoomId(1);
        testConference.setStartTime(LocalDateTime.now().plusDays(2));
        testConference.setEndTime(LocalDateTime.now().plusDays(2).plusHours(1));
        testConference.setCanceled(false);
    }

    @Test
    void shouldReturnAllConferencesInTimeRange() {
        LocalDateTime now = LocalDateTime.now();
        when(conferenceRepository.getConferencesInTimeRange(Mockito.any(), Mockito.any())).thenReturn(List.of(testConference));

        List<Conference> result = conferenceService.getAllConferencesBetweenTimeRange(now.minusHours(10), now.plusHours(10));

        assertEquals(1, result.size());
        assertEquals("TestConf", result.getFirst().getName());
    }

    @Test
    void shouldReturnConferenceById() {
        when(conferenceRepository.findById(1)).thenReturn(testConference);

        Conference result = conferenceService.findConferenceById(1);

        assertNotNull(result);
        assertEquals("TestConf", result.getName());
    }

    @Test
    void shouldThrowIfConferenceNameAlreadyExists() {
        Conference existing = new Conference();
        existing.setName("TestConf");

        when(conferenceRepository.getConferencesInTimeRange(Mockito.any(), Mockito.any())).thenReturn(List.of(existing));

        GeneralException ex = assertThrows(GeneralException.class, () -> {
            conferenceService.addNewConference(testConference);
        });

        assertEquals("Conference with that name already exists", ex.getMessage());
    }

    @Test
    void shouldThrowIfRoomIsBookedForRequestedTime() {
        Conference conflicting = new Conference();
        conflicting.setName("Other");
        conflicting.setRoomId(1);
        conflicting.setStartTime(testConference.getStartTime().minusMinutes(10));
        conflicting.setEndTime(testConference.getStartTime().plusMinutes(30));
        conflicting.setCanceled(false);

        when(conferenceRepository.getConferencesInTimeRange(Mockito.any(), Mockito.any()))
                .thenReturn(List.of(conflicting));

        GeneralException ex = assertThrows(GeneralException.class, () -> {
            conferenceService.addNewConference(testConference);
        });

        assertEquals("Room is already booked for that time", ex.getMessage());
    }

    @Test
    void shouldThrowIfConferenceLessThanDayInAdvance() {
        testConference.setStartTime(LocalDateTime.now().minusHours(1));

        when(conferenceRepository.getConferencesInTimeRange(Mockito.any(), Mockito.any())).thenReturn(List.of());

        GeneralException ex = assertThrows(GeneralException.class, () -> {
            conferenceService.addNewConference(testConference);
        });

        assertEquals("Conference is less than a day in advance", ex.getMessage());
    }
}
