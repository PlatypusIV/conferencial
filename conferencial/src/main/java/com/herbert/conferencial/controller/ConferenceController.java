package com.herbert.conferencial.controller;

import com.herbert.conferencial.model.Conference;
import com.herbert.conferencial.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConferenceController {


    private final ConferenceService conferenceService;

    @Autowired
    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @GetMapping(path = "/")
    public String getMainPage(){
        return "Initial website";
    };

    @GetMapping(path = "/conferences")
    public ResponseEntity<List<Conference>> getAllConferences(Pageable page) {
        var conferenceList = conferenceService.getAllConferences(page).toList();
        return ResponseEntity.ok(conferenceList);
    }

    @PostMapping("/conferences")
    public ResponseEntity<Conference> addNewConference(@RequestBody Conference conference){
        var newlyCreatedConference = conferenceService.addNewConference(conference);
        return ResponseEntity.ok(newlyCreatedConference);
    }
}
