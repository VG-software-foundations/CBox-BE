package com.example.cbox.dto.create;

import jakarta.validation.constraints.NotBlank;

public record UserLoginDto(
        @NotBlank
        String password,
        @NotBlank
        String username

) {
}
