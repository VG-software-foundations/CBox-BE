package com.example.cbox.controller;

import com.example.cbox.annotation.ValidatedController;
import com.example.cbox.dto.create.Code;
import com.example.cbox.dto.create.UserAuthDto;
import com.example.cbox.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@ValidatedController
@RequestMapping(value = "/verify")
@RequiredArgsConstructor
@Slf4j
public class VerificationController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> verify(
            @AuthenticationPrincipal UserAuthDto dto,
            @RequestBody Code code
    ) {
        return userService.verify(dto.id(), code.code())
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().body("There is no match");
    }
}
