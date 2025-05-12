package com.herbert.conferencial.repository;

import com.herbert.conferencial.model.Participant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class ParticipantRepositoryTest {

    @Autowired
    private ParticipantRepository participantRepository;

    @Test
    @Transactional
    @Rollback
    @DisplayName("Should save and find participant by ID")
    void shouldFindParticipantById() {
        Participant participant = new Participant();
        participant.setFullName("Test1");
        participant.setConferenceId(101);

        Participant saved = participantRepository.save(participant);

        Participant found = participantRepository.findParticipantById(1);
        assertThat(found).isNotNull();
        assertThat(found.getFullName()).isEqualTo("Test1");
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("Should find all participants by conference ID")
    void shouldFindAllParticipantsByConferenceId() {
        Participant p1 = new Participant();
        p1.setFullName("Test1");
        p1.setConferenceId(200);

        Participant p2 = new Participant();
        p2.setFullName("Test2");
        p2.setConferenceId(200);

        participantRepository.save(p1);
        participantRepository.save(p2);

        List<Participant> participants = participantRepository.findAllParticipantsByConferenceId(200);
        assertThat(participants).hasSize(2);
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("Should count participants in a conference")
    void shouldCountParticipantsInConference() {
        Participant p1 = new Participant();
        p1.setFullName("Test1");
        p1.setConferenceId(300);

        Participant p2 = new Participant();
        p2.setFullName("Test2");
        p2.setConferenceId(300);

        participantRepository.save(p1);
        participantRepository.save(p2);

        int count = participantRepository.findAmountOfParticipantsInConference(300);
        assertEquals(count, 2);
    }
}
