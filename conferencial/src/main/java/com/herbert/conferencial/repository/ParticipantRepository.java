package com.herbert.conferencial.repository;

import com.herbert.conferencial.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Integer> {

    List<Participant> findAllParticipantsByConferenceId(int conferenceId);
    Participant findParticipantById(int id);

}
