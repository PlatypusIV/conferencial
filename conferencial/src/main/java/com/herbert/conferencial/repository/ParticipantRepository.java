package com.herbert.conferencial.repository;

import com.herbert.conferencial.model.Participant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Integer> {
//    Page<Participant> findAllParticipants(Pageable page);
//    Participant findParticipantById(long id);

}
