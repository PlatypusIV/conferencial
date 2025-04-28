package com.herbert.conferencial.service;

import com.herbert.conferencial.model.Conference;
import com.herbert.conferencial.repository.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConferenceService {


    private final ConferenceRepository conferenceRepository;

    @Autowired
    public ConferenceService(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    public Page<Conference> getAllConferences(Pageable page) {
        return conferenceRepository.findAll(page);
    }

    public Conference addNewConference(Conference conference){
        return conferenceRepository.save(conference);
    }

    public Conference findConferenceById(int id){
        return conferenceRepository.findById(id);
    }
}
