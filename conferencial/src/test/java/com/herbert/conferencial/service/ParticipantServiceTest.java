package com.herbert.conferencial.service;

import com.herbert.conferencial.errorHandling.GeneralException;
import com.herbert.conferencial.model.Conference;
import com.herbert.conferencial.model.Participant;
import com.herbert.conferencial.model.Room;
import com.herbert.conferencial.repository.ParticipantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ParticipantServiceTest {

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private ConferenceService conferenceService;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private ParticipantService participantService;

    private Participant testParticipant;

    @BeforeEach
    void beforeEach() {
        testParticipant = new Participant();
        testParticipant.setFullName("Test1");
        testParticipant.setBirthDate(LocalDate.of(2000, 1, 1));
        testParticipant.setConferenceId(1);
    }

    @Test
    void shouldThrowIfParticipantIsUnder18() {
        testParticipant.setBirthDate(LocalDate.now().minusYears(17));

        GeneralException ex = assertThrows(GeneralException.class, () ->
                participantService.validateParticipantAge(testParticipant)
        );

        assertEquals("Participant is not an adult", ex.getMessage());
    }

    @Test
    void shouldThrowIfParticipantIsAlreadyInConference() {
         when(participantService.getParticipantsRelatedToConference(1)).thenReturn(List.of(testParticipant));


        GeneralException ex = assertThrows(GeneralException.class, () ->
                participantService.validateRepeatingParticipants(testParticipant)
        );

        assertEquals("Participant is already taking part of conference", ex.getMessage());
    }

    @Test
    void shouldThrowIfConferenceIsFull() {
        when(participantRepository.findAmountOfParticipantsInConference(1)).thenReturn(100);
        when(conferenceService.findConferenceById(1)).thenReturn(new Conference());
        when(roomService.getRoomById(0)).thenReturn(new Room() {{ setMaxSeats(1); }});

        GeneralException ex = assertThrows(GeneralException.class, () ->
                participantService.validateAmountOfParticipants(1)
        );

        assertEquals("Too many participants in conference", ex.getMessage());
    }

    @Test
    void shouldSaveValidParticipant() {
        when(participantRepository.findAllParticipantsByConferenceId(1))
                .thenReturn(List.of());

        when(participantRepository.findAmountOfParticipantsInConference(1)).thenReturn(10);
        when(conferenceService.findConferenceById(1)).thenReturn(new Conference() {{ setRoomId(2); }});
        when(roomService.getRoomById(2)).thenReturn(new Room() {{ setMaxSeats(100); }});
        when(participantRepository.save(testParticipant)).thenReturn(testParticipant);

        Participant saved = participantService.addNewParticipant(testParticipant);

        assertNotNull(saved);
        assertEquals(testParticipant.getFullName(), saved.getFullName());
    }

}