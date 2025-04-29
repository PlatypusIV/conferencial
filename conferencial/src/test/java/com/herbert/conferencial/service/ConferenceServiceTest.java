package com.herbert.conferencial.service;

import com.herbert.conferencial.model.Conference;
import com.herbert.conferencial.repository.ConferenceRepository;
import com.herbert.conferencial.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class ConferenceServiceTest {

    @Mock
    RoomRepository roomRepository;

    @InjectMocks
    RoomService roomService;

    @Mock
    ConferenceRepository conferenceRepository;

    @InjectMocks
    ConferenceService conferenceService;

    Conference testConference = new Conference();
    Conference testConference2 = new Conference();
    Conference testConferenceInvalid = new Conference();

    @BeforeEach
    public void beforeEach() {
        testConference.setName("test 1");
        testConference.setStartTime(LocalDateTime.now());
        testConference.setEndTime(LocalDateTime.now().plusHours(1));
        testConference.setRoomId(1);
        testConference.setCanceled(false);

        testConference2.setName("test 2");
        testConference2.setStartTime(LocalDateTime.now());
        testConference2.setEndTime(LocalDateTime.now().plusHours(1));
        testConference2.setRoomId(2);
        testConference2.setCanceled(false);

        testConferenceInvalid.setName("test 2");
        testConferenceInvalid.setStartTime(LocalDateTime.now());
        testConferenceInvalid.setEndTime(LocalDateTime.now().plusHours(1));
        testConferenceInvalid.setRoomId(2);
        testConferenceInvalid.setCanceled(false);
    }



}
