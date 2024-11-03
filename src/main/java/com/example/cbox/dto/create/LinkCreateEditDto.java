package com.example.cbox.dto.create;

import java.util.UUID;

public record LinkCreateEditDto(
        UUID userId,
        Long fileId
) {
}
