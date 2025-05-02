package com.herbert.conferencial.controller;

import com.herbert.conferencial.model.Participant;
import com.herbert.conferencial.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
