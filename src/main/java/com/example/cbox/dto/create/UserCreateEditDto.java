package com.example.cbox.dto.create;

import com.example.cbox.enumeration.Role;

import java.util.UUID;

public record UserCreateEditDto(
        UUID id,
        String email,
        String firstName,
        String secondName,
        String username,
        String phoneNumber,
        Role role
) {
}
