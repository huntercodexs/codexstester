package com.huntercodexs.sample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("${api.prefix}")
public class SampleController {

    @PostMapping(path = "/sample")
    @ResponseBody
    public ResponseEntity<String> sample() {
        return ResponseEntity.ok().body("Welcome to sample from Codexs Tester");
    }

}
