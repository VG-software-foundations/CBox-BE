package com.example.cbox.controller;

import com.example.cbox.annotation.ValidatedController;
import com.example.cbox.dto.create.UserCreateEditDto;
import com.example.cbox.dto.read.PageResponse;
import com.example.cbox.dto.read.UserReadDto;
import com.example.cbox.service.UserService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.ResponseEntity.*;

@ValidatedController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<PageResponse<UserReadDto>> findAll(@RequestParam(defaultValue = "1") @Min(1) Integer page,
                                                             @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer limit) {
        return ok().body(PageResponse.of(userService.findAll(page, limit)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserReadDto> findById(@PathVariable("id") String id) {
        return userService.findById(UUID.fromString(id))
                .map(obj -> ok()
                        .body(obj))
                .orElseGet(notFound()::build);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserReadDto> create(@RequestBody @Validated UserCreateEditDto user) {

        return ok().body(userService.create(user));
    }

    @PutMapping()
    public ResponseEntity<UserReadDto> update(@RequestBody @Validated UserCreateEditDto user) {
        return userService.update(user.id(), user)
                .map(obj -> ok().body(obj))
                .orElseGet(notFound()::build);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody @Validated UserCreateEditDto user) {
        return userService.delete(user.id())
                ? noContent().build()
                : notFound().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserReadDto> findSelf(@RequestBody @Validated UserCreateEditDto user) {
        return userService.findById(user.id())
                .map(obj -> ok()
                        .body(obj))
                .orElseGet(notFound()::build);
    }
}
