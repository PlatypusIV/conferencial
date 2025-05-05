package com.herbert.conferencial.repository;

import jakarta.persistence.EntityManager;
import com.herbert.conferencial.model.Conference;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
class ConferenceRepositoryTest {

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    @Rollback
    @DisplayName("Should save and retrieve conference by ID")
    void shouldFindConferenceById() {
        Conference conference = new Conference();
        conference.setName("TestConf1");
        conference.setStartTime(LocalDateTime.now());
        conference.setEndTime(LocalDateTime.now().plusHours(2));
        conference.setCanceled(false);

        conferenceRepository.save(conference);

        Conference found = conferenceRepository.findById(1);
        assertNotNull(found);
        assertEquals(found.getName(), "TestConf1");
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("Should toggle isCanceled flag")
    void shouldSetConferenceToCanceled() {
        Conference conference = new Conference();
        conference.setName("TestConference");
        conference.setStartTime(LocalDateTime.now());
        conference.setEndTime(LocalDateTime.now().plusHours(3));
        conference.setCanceled(false);

        Conference created = conferenceRepository.save(conference);
        conferenceRepository.setConferenceToCanceled(created.getId());

        entityManager.flush();
        entityManager.clear();

        Conference updated = conferenceRepository.findById(created.getId());

        assertTrue(updated.isCanceled());
        assertEquals(updated.getName(), conference.getName());
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("Should find conferences in time range")
    void shouldFindConferencesInTimeRange() {
        LocalDateTime now = LocalDateTime.now();

        Conference conf1 = new Conference();
        conf1.setName("TestConference1");
        conf1.setStartTime(now.minusHours(1));
        conf1.setEndTime(now.plusHours(1));
        conf1.setCanceled(false);

        Conference conf2 = new Conference();
        conf2.setName("TestConference2");
        conf2.setStartTime(now.plusHours(5));
        conf2.setEndTime(now.plusHours(6));
        conf2.setCanceled(false);

        conferenceRepository.save(conf1);
        conferenceRepository.save(conf2);

        List<Conference> found = conferenceRepository.getConferencesInTimeRange(now.minusHours(2), now.plusHours(2));
        assertEquals(found.size(), 1);
        assertEquals(found.getFirst().getName(), "TestConference1");
    }
}
