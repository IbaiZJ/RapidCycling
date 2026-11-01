package com.rapidcycling.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class PublicController {

    private static final Logger log = LoggerFactory.getLogger(PublicController.class);

    @GetMapping("/")
    public String index() {
        log.info("Index page accessed");
        return "pages/index";
    }

    @GetMapping(value = "/status", produces = "application/json")
    public ResponseEntity<String> getPageStatus() {
        return new ResponseEntity<>("Page is running successfully", HttpStatus.OK);
    }

}
