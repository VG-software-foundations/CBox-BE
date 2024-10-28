package com.example.cbox.dto.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public record UserData(
        @JsonProperty("first_name")
        @NotBlank
        String firstName,
        @JsonProperty("second_name")
        @NotBlank
        String secondName,
        @JsonProperty("username")
        @NotBlank
        String username,
        @Pattern(regexp = "^\\+\\d{1,3}\\d{6,14}$")
        String phoneNumber
) {
}
