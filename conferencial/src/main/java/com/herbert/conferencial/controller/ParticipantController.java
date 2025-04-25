package com.herbert.conferencial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParticipantController {

    @GetMapping(path = "/participants/{conferenceId}")
    public String getParticipants(@PathVariable("conferenceId") int conferenceId){

        return String.format("Id of conference was %d", conferenceId);
    }
}
