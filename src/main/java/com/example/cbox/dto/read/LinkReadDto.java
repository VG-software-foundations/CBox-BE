package com.example.cbox.dto.read;

import java.util.UUID;

public record LinkReadDto(
        UUID userId,
        Long fileId
) {
}
