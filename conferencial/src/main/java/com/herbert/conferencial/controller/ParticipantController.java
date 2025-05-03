package com.herbert.conferencial.controller;

import com.herbert.conferencial.model.Participant;
import com.herbert.conferencial.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    @Autowired
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping(path = "/{conferenceId}")
    public ResponseEntity<List<Participant>> getParticipantsForConference(@PathVariable("conferenceId") int conferenceId) {
        List<Participant> conferenceParticipants = participantService.getParticipantsRelatedToConference(conferenceId);
        return ResponseEntity.ok(conferenceParticipants);
    }

    @PostMapping(path = "/")
    public ResponseEntity<?> addNewParticipant(@RequestBody Participant participant){
        boolean isValidParticipant = participantService.addNewParticipant(participant);
        if(isValidParticipant) return new ResponseEntity<>("Created", HttpStatus.CREATED);
        return new ResponseEntity<>("Incorrect participant", HttpStatus.UNPROCESSABLE_ENTITY);
    }


}
