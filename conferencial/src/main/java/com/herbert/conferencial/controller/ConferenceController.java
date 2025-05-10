package com.herbert.conferencial.controller;

import com.herbert.conferencial.model.Conference;
import com.herbert.conferencial.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/conferences")
public class ConferenceController {

    private final ConferenceService conferenceService;

    @Autowired
    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @GetMapping
    public ResponseEntity<List<Conference>> getAllConferencesBetweenTimeRange(@RequestParam String start, @RequestParam String end) {
        LocalDateTime startTime = LocalDateTime.parse(start);
        LocalDateTime endTime = LocalDateTime.parse(end);
        var conferenceList = conferenceService.getAllConferencesBetweenTimeRange(startTime, endTime);
        return ResponseEntity.ok(conferenceList);
    }

    @PostMapping(path = "/")
    public ResponseEntity<?> addNewConference(@RequestBody Conference conference) {
        var createdConference = conferenceService.addNewConference(conference);
        return new ResponseEntity<>(createdConference, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Conference> getConferenceById(@PathVariable("id") int id) {
        Conference conference = conferenceService.findConferenceById(id);
        return ResponseEntity.ok(conference);
    }

    @PatchMapping(path = "/cancel/{id}")
    public ResponseEntity<?> cancelConference(@PathVariable("id") int id){
        conferenceService.cancelOrUnCancelConference(id);
        return new ResponseEntity<>("Canceled", HttpStatus.OK);
    }
}
