package com.herbert.conferencial.service;

import com.herbert.conferencial.errorHandling.GeneralException;
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

    public Conference findConferenceById(int id) {
        return conferenceRepository.findById(id);
    }

    public void cancelOrUnCancelConference(int id) {
        conferenceRepository.setConferenceToCanceled(id);
    }

    public Conference addNewConference(Conference conference) {
        validateConference(conference);
        return conferenceRepository.save(conference);
    }


    private void validateConference(Conference conference) {
        LocalDate localDate = LocalDate.parse(conference.getStartTime().toLocalDate().toString());

        LocalDateTime startOfDay = localDate.atStartOfDay();
        LocalDateTime endOfDay = localDate.atTime(23, 59);

        List<Conference> conferencesHappeningThatDay = conferenceRepository.getConferencesInTimeRange(startOfDay, endOfDay);

        boolean isInvalidName = conferencesHappeningThatDay.stream().anyMatch(c ->
                c.getName().equals(conference.getName()));

        if (isInvalidName) {
            throw new GeneralException("Conference with that name already exists");
        }

        boolean isInvalidRoomId = validateRoomOccupation(conferencesHappeningThatDay.stream().filter(c -> c.getRoomId() == conference.getRoomId() && !c.isCanceled()).toList(), conference);
        if (isInvalidRoomId) {
            throw new GeneralException("Room is already booked for that time");
        }

        boolean isLessThanDayInAdvance = conference.getStartTime().isAfter(LocalDateTime.now().minusDays(1)) && conference.getStartTime().isBefore(LocalDateTime.now());
        if (isLessThanDayInAdvance) {
            throw new GeneralException("Conference is less than a day in advance");
        }
    }

    private boolean validateRoomOccupation(List<Conference> conferencesToCompareTo, Conference conferenceInComparison) {
        boolean isInvalidStartTime = conferencesToCompareTo.stream().anyMatch(c -> (c.getStartTime().isBefore(conferenceInComparison.getStartTime()) || c.getStartTime().equals(conferenceInComparison.getStartTime())) && c.getEndTime().isAfter(conferenceInComparison.getStartTime()));

        boolean isInvalidEndTime = conferencesToCompareTo.stream().anyMatch(c -> (c.getEndTime().isAfter(conferenceInComparison.getEndTime()) || c.getEndTime().equals(conferenceInComparison.getEndTime())) && c.getStartTime().isBefore(conferenceInComparison.getEndTime()));

        boolean areBothEndAndStartTimeInvalid = conferencesToCompareTo.stream().anyMatch(c -> conferenceInComparison.getStartTime().isBefore(c.getStartTime()) && conferenceInComparison.getEndTime().isAfter(c.getEndTime()));

        return isInvalidStartTime || isInvalidEndTime || areBothEndAndStartTimeInvalid;
    }
}
