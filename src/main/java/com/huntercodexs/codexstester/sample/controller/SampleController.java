package com.huntercodexs.codexstester.sample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class SampleController {

    @GetMapping("/codexstester/api/sample/welcome")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok().body("Welcome to codexstester sample API");
    }

    @GetMapping(path = "/codexstester/api/sample/get/{id}")
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable int id) {
        return new ResponseEntity<>("GET OK - " + id, HttpStatus.OK);
    }

    @PostMapping(path = "/codexstester/api/sample/post/{id}")
    @ResponseBody
    public ResponseEntity<?> post(@PathVariable int id) {
        return new ResponseEntity<>("POST OK - " + id, HttpStatus.CREATED);
    }

    @PutMapping(path = "/codexstester/api/sample/put/{id}")
    @ResponseBody
    public ResponseEntity<?> put(@PathVariable int id) {
        return new ResponseEntity<>("PUT OK - " + id, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/codexstester/api/sample/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>("DELETE OK - " + id, HttpStatus.ACCEPTED);
    }

    @PatchMapping(path = "/codexstester/api/sample/patch/{id}")
    @ResponseBody
    public ResponseEntity<?> patch(@PathVariable int id) {
        return new ResponseEntity<>("PATCH OK - " + id, HttpStatus.ACCEPTED);
    }

    @GetMapping("/codexstester/api/sample/counter/{counter}")
    public ResponseEntity<String> counter(@PathVariable(name = "counter") String counter) {

        if (counter.equals("1")) {
            return ResponseEntity.ok().body("One...");
        } else if (counter.equals("2")) {
            return ResponseEntity.ok().body("Two...");
        } else if (counter.equals("3")) {
            return ResponseEntity.ok().body("Three...");
        } else {
            return ResponseEntity.ok().body("Go !");
        }

    }

}
