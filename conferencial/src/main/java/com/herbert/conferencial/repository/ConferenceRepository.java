package com.herbert.conferencial.repository;

import com.herbert.conferencial.model.Conference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConferenceRepository extends JpaRepository<Conference, Integer> {
    Page<Conference> findAll(Pageable page);

    Conference findById(int id);

    List<Conference> findByLocation(String location, Pageable page);


}
