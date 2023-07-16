package com.huntercodexs.sample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("${api.prefix}")
public class SampleController {

    @GetMapping(path = "/sample")
    @ResponseBody
    public ResponseEntity<String> getSample() {
        System.out.println("!!! WELCOME GET !!!");
        return ResponseEntity
                .ok()
                .body("Welcome to sample from Codexs Tester");
    }

    @PostMapping(path = "/sample", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> postSample() {
        System.out.println("!!! WELCOME POST !!!");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("{\"message\": \"Welcome to sample from Codexs Tester\"}");
    }

}
