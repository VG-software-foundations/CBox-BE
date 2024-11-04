package com.example.cbox.dto.create;

import com.example.cbox.enumeration.Role;

import java.util.List;
import java.util.UUID;

public record UserAuthDto(
        UUID id,
        String password,
        String username,
        List<Role> authorities
) {
}
