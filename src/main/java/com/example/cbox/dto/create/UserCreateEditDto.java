package com.example.cbox.dto.create;

import java.util.UUID;

public record UserCreateEditDto(
        UUID id,
        String email,
        String firstName,
        String secondName,
        String username,
        String phoneNumber
) {
}
