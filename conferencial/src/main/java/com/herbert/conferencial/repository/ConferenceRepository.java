package com.herbert.conferencial.repository;

import com.herbert.conferencial.model.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface ConferenceRepository extends JpaRepository<Conference, Integer> {
    @Query(value= "SELECT c FROM Conference c WHERE id = :id")
    Conference findById(@Param("id")int id);

    @Modifying
    @Transactional
    @Query("UPDATE Conference c SET c.isCanceled = NOT c.isCanceled WHERE c.id = :id")
    void setConferenceToCanceled(int id);

    @Query(value = "SELECT c" +
            " FROM Conference c" +
            " WHERE c.startTime BETWEEN :start AND :end")
    List<Conference> getConferencesInTimeRange(LocalDateTime start, LocalDateTime end);
}
