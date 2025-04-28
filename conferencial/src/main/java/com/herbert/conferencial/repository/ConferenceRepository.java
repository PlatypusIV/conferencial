package com.herbert.conferencial.repository;

import com.herbert.conferencial.model.Conference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConferenceRepository extends JpaRepository<Conference, Integer> {
    Page<Conference> findAll(Pageable page);

//    @Query(value= "SELECT id FROM conference WHERE id = :id")
    Conference findById(@Param("id")int id);

    List<Conference> findByRoomId(long room_id, Pageable page);

    //find conferences in between times

}
