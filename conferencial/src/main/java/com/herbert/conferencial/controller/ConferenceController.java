package com.herbert.conferencial.controller;

import com.herbert.conferencial.model.Conference;
import com.herbert.conferencial.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<Conference>> getAllConferences() {
        var conferenceList = conferenceService.getAllConferences();
        return ResponseEntity.ok(conferenceList);
    }

    @PostMapping(path = "/")
    public ResponseEntity<?> addNewConference(@RequestBody Conference conference){
        boolean result = conferenceService.addNewConference(conference);
        if(result) return new ResponseEntity<>("Created", HttpStatus.CREATED);
        return new ResponseEntity<>("Bad Request", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Conference> getConferenceById(@PathVariable("id") int id) {
        Conference conference = conferenceService.findConferenceById(id);
        return ResponseEntity.ok(conference);
    }

    @PatchMapping(path = "/cancel")
    public ResponseEntity<?> cancelConference(@RequestBody int id){
        boolean result = conferenceService.cancelOrUnCancelConference(id);
        if(result) return new ResponseEntity<>("Canceled", HttpStatus.OK);
        return new ResponseEntity<>("Incorrect input", HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
