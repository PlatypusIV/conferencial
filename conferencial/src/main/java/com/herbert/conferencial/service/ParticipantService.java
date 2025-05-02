package com.herbert.conferencial.service;

import com.herbert.conferencial.model.Participant;
import com.herbert.conferencial.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.util.List;

@Service
public class ParticipantService {
    private final ParticipantRepository participantRepository;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public List<Participant> getParticipantsRelatedToConference(int conferenceId) {
        return participantRepository.findAllParticipantsByConferenceId(conferenceId);
    }

    public boolean addNewParticipant(Participant participant) {
        boolean isAdult = validateParticipantAge(participant);
        boolean isUniqueInConference = validateRepeatingParticipants(participant);
        if(isAdult && isUniqueInConference) {
            participantRepository.save(participant);
            return true;
        }
        return false;
    }

    public boolean validateParticipantAge(Participant participant) {
        return Period.between(participant.getBirthDate(), LocalDate.now()).getYears() >= 18;
    }

    public boolean validateRepeatingParticipants(Participant participant) {
        List<Participant> participantsOfConference = participantRepository.findAllParticipantsByConferenceId(participant.getConferenceId());

        return participantsOfConference.stream().noneMatch(p -> p.getFullName().equals(participant.getFullName()) && p.getBirthDate().equals(participant.getBirthDate()));
    }

}
