package com.herbert.conferencial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConferenceController {

    @GetMapping(path = "/")
    public String getMainPage(){
        return "Initial website";
    };
}
