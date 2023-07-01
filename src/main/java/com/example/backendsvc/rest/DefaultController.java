package com.example.backendsvc.rest;

import com.example.backendsvc.data.dto.WelcomeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class DefaultController {
    @GetMapping("/hello")
    public ResponseEntity<WelcomeDTO> hello() {

        return ResponseEntity.ok(WelcomeDTO.builder()
                .message("Hello from Spring Boot")
                .localDateTime(LocalDateTime.now().toString())
                .build());
    }
    @GetMapping("/hello/{name}")
    public ResponseEntity<WelcomeDTO> helloName(@PathVariable String name) {
        return ResponseEntity.ok(WelcomeDTO.builder()
                .message("Hello " + name + " from Spring Boot")
                .localDateTime(LocalDateTime.now().toString())
                .build());
    }
    @PostMapping
    public ResponseEntity<WelcomeDTO> helloPost(@RequestBody String name) {
        return ResponseEntity.ok(WelcomeDTO.builder()
                .message("Hello " + name + " from Spring Boot")
                .localDateTime(LocalDateTime.now().toString())
                .build());
    }
}
