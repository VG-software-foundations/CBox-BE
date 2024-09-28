package com.example.cbox.dto.read;

public record UserReadDto(
        String email,
        String firstName,
        String secondName,
        String username,
        String phoneNumber
) {
}
