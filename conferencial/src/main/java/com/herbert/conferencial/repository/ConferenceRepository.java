package com.herbert.conferencial.repository;

import com.herbert.conferencial.model.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ConferenceRepository extends JpaRepository<Conference, Integer> {
    List<Conference> findAll();

//    @Query(value= "SELECT id FROM conference WHERE id = :id")
    Conference findById(@Param("id")int id);

    List<Conference> findByRoomId(int room_id);

//    @Query("UPDATE conference c SET c.is_canceled = :isCanceled WHERE c.id = :id")
//    Conference setConferenceToCanceled(int id, boolean isCanceled);
}
