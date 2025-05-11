package com.herbert.conferencial.service;

import com.herbert.conferencial.errorHandling.GeneralException;
import com.herbert.conferencial.model.Participant;
import com.herbert.conferencial.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class ParticipantService {

    private final ConferenceService conferenceService;

    private final RoomService roomService;

    private final ParticipantRepository participantRepository;

    @Autowired
    public ParticipantService(ConferenceService conferenceService, RoomService roomService, ParticipantRepository participantRepository) {
        this.conferenceService = conferenceService;
        this.roomService = roomService;
        this.participantRepository = participantRepository;
    }

    public List<Participant> getParticipantsRelatedToConference(int conferenceId) {
        return participantRepository.findAllParticipantsByConferenceId(conferenceId);
    }

    public Participant addNewParticipant(Participant participant) {
        validateParticipantAge(participant);
        validateRepeatingParticipants(participant);
        validateAmountOfParticipants(participant.getConferenceId());
        return participantRepository.save(participant);
    }

    public void removeParticipantFromConference(int id) {
        participantRepository.deleteById(id);
    }

    public void validateParticipantAge(Participant participant) {
        if(!(Period.between(participant.getBirthDate(), LocalDate.now()).getYears() >= 18)){
            throw new GeneralException("Participant is not an adult");
        }
    }

    public void validateRepeatingParticipants(Participant participant) {
        List<Participant> participantsOfConference = participantRepository.findAllParticipantsByConferenceId(participant.getConferenceId());
        if((participantsOfConference.stream().anyMatch(p -> p.getFullName().equals(participant.getFullName()) && p.getBirthDate().equals(participant.getBirthDate())))){
            throw new GeneralException("Participant is already taking part of conference");
        }
    }

    public void validateAmountOfParticipants(int conferenceId){

        int amountOfParticipants = participantRepository.findAmountOfParticipantsInConference(conferenceId);

        int roomId = conferenceService.findConferenceById(conferenceId).getRoomId();

        int roomMaxSeats = roomService.getRoomById(roomId).getMaxSeats();

        if(amountOfParticipants>= roomMaxSeats){
            throw new GeneralException("Too many participants in conference");
        }
    }

}
