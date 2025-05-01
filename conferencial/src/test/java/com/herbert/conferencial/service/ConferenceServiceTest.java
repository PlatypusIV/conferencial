package com.herbert.conferencial.service;

import com.herbert.conferencial.model.Conference;
import com.herbert.conferencial.model.Room;
import com.herbert.conferencial.repository.ConferenceRepository;
import com.herbert.conferencial.repository.ParticipantRepository;
import com.herbert.conferencial.repository.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ConferenceServiceTest {

    @Mock
    RoomRepository roomRepository;

    @Mock
    RoomService roomService;

    @Mock
    ParticipantRepository participantRepository;

    @Mock
    ParticipantService participantService;

    @Mock
    ConferenceRepository conferenceRepository;

    @InjectMocks
    ConferenceService conferenceService;

    Room testRoom = new Room();
    Room testRoom2 = new Room();
    Room testRoom3 = new Room();

    Conference testConference = new Conference();
    Conference testConference2 = new Conference();
    Conference testConferenceInvalid = new Conference();

    List<Conference> listOfTestConferences;

    @BeforeEach
    public void beforeEach() {
        testRoom.setName("Room1");
        testRoom.setLocation("street 1");
        testRoom.setMaxSeats(10);

        testRoom2.setName("Room2");
        testRoom2.setLocation("street 2");
        testRoom2.setMaxSeats(200);

        testRoom3.setName("Room3");
        testRoom3.setLocation("street 3");
        testRoom3.setMaxSeats(999);
        
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

        listOfTestConferences = List.of(testConference, testConference2);


    }

    @Test
    public void addNewConferenceShouldSucceed() {
//        Mockito.when(conferenceRepository.getConferencesInTimeRange(Mockito.any(),Mockito.any())).thenReturn(List.of(testConference2));
//
//        Mockito.when(conferenceService.addNewConference(testConference)).thenReturn(true);
//
//        boolean result = conferenceService.addNewConference(testConference);
//
//        Assertions.assertNotEquals(null, result);
////        Assertions.assertEquals(result.getName(), testConference.getName());
    }

}
