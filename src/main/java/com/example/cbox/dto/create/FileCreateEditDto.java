package com.example.cbox.dto.create;

import com.example.cbox.enumeration.FileAccessType;

public record FileCreateEditDto(
        Long id,
        String fileName,
        FileAccessType accessType
){
}
