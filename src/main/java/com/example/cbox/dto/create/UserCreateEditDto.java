package com.example.cbox.dto.create;

import com.example.cbox.enumeration.Role;


public record UserCreateEditDto(
        String password,
        String username,
        Role role
) {
}
