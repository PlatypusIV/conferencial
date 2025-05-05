package com.herbert.conferencial.repository;

import com.herbert.conferencial.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Integer> {

    List<Participant> findAllParticipantsByConferenceId(int conferenceId);
    Participant findParticipantById(int id);

    @Query("SELECT COUNT(*) FROM Participant p WHERE p.conferenceId = :conferenceId")
    int findAmountOfParticipantsInConference(int conferenceId);

}
