package com.herbert.conferencial.repository;

import com.herbert.conferencial.model.Conference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ConferenceRepositoryTest {
    @Mock
    private ConferenceRepository conferenceRepository;

    private Conference testConference;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testConference = new Conference("Conf 1", LocalDateTime.now().minusHours(1),
                LocalDateTime.now().plusHours(1), 1, false);
        List<Conference> conferenceList = List.of(testConference);

        when(conferenceRepository.findAll()).thenReturn(conferenceList);
        when(conferenceRepository.findById(1)).thenReturn(testConference);
    }

    @Test
    void findAllShouldReturnListWithAllConferences() {
        List<Conference> allConferences = conferenceRepository.findAll();
        assertEquals(1, allConferences.size());
        assertTrue(allConferences.contains(testConference));
    }

    @Test
    void findByIdShouldReturntestConference() {
        Conference foundConference = conferenceRepository.findById(1);
        assertEquals("Conf 1", foundConference.getName());
    }

    @Test
    void getConferencesInTimeRangeShouldReturnListWithTestConference() {
        LocalDateTime startTime = LocalDateTime.now().minusHours(2);
        LocalDateTime endTime = LocalDateTime.now().plusHours(2);

        List<Conference> expectedConferences = List.of(testConference);

        when(conferenceRepository.getConferencesInTimeRange(startTime, endTime)).thenReturn(expectedConferences);

        List<Conference> conferencesInTimeRange = conferenceRepository.getConferencesInTimeRange(startTime, endTime);

        assertEquals(expectedConferences, conferencesInTimeRange);
    }

    @Test
    void setConferenceToCanceledShouldCancelConferenceToCancel() {
        Conference canceledConference = new Conference("Canceled conference", LocalDateTime.now(),
                LocalDateTime.now().plusHours(2), 1, true);

        when(conferenceRepository.setConferenceToCanceled(2)).thenReturn(canceledConference);

        Conference result = conferenceRepository.setConferenceToCanceled(2);

        assertTrue(result.isCanceled());
    }
}