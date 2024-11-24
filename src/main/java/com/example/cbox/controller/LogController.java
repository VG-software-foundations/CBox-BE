package com.example.cbox.controller;

import com.example.cbox.annotation.ValidatedController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@ValidatedController
@RequestMapping(value = "/logs")
@RequiredArgsConstructor
@Slf4j
public class LogController {

    @PostMapping
    public ResponseEntity<?> verify(
            @RequestBody String log
    ) {
        return ResponseEntity.ok().build();
    }
}
