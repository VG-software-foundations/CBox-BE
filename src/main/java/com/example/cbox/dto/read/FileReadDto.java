package com.example.cbox.dto.read;

import java.util.List;

public record FileReadDto(
        Long id,
        String link,
        String type,
        List<UserReadDto> users
) {
}
