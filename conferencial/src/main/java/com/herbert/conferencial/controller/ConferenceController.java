package com.herbert.conferencial.controller;

import com.herbert.conferencial.model.Conference;
import com.herbert.conferencial.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conferences")
public class ConferenceController {

    private final ConferenceService conferenceService;

    @Autowired
    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<Conference>> getAllConferences(Pageable page) {
        var conferenceList = conferenceService.getAllConferences(page).toList();
        return ResponseEntity.ok(conferenceList);
    }

    @PostMapping(path = "/")
    public ResponseEntity<Conference> addNewConference(@RequestBody Conference conference){
        var newlyCreatedConference = conferenceService.addNewConference(conference);
        return ResponseEntity.ok(newlyCreatedConference);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Conference> getConferenceById(@PathVariable("id") int id) {
        Conference conference = conferenceService.findConferenceById(id);
        return ResponseEntity.ok(conference);
    }
}
