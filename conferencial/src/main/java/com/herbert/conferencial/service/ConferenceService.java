package com.herbert.conferencial.service;

import com.herbert.conferencial.model.Conference;
import com.herbert.conferencial.repository.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConferenceService {


    private final ConferenceRepository conferenceRepository;

    @Autowired
    public ConferenceService(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    public List<Conference> getAllConferences() {
        return conferenceRepository.findAll();
    }

    public Conference addNewConference(Conference conference){

        boolean test = validateConference(conference);
        return conferenceRepository.save(conference);
    }

    public Conference findConferenceById(int id){
        return conferenceRepository.findById(id);
    }

    private boolean validateConference(Conference conference) {
        LocalDate localDate = LocalDate.parse(conference.getStartTime().toLocalDate().toString());

        LocalDateTime startOfDay = localDate.atStartOfDay();
        LocalDateTime endOfDay = localDate.atTime(23,59);

        List<Conference> conferencesHappeningThatDay = conferenceRepository.getConferencesInTimeRange(startOfDay, endOfDay);

        boolean isInvalidName = conferencesHappeningThatDay.stream().anyMatch(c->
                c.getName().equals(conference.getName()));


        boolean isInvalidRoomId = conferencesHappeningThatDay.stream().anyMatch(c -> c.getRoomId() == conference.getRoomId());

        return !isInvalidRoomId && !isInvalidName;
    }
}
