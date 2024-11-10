package com.example.cbox.dto.create;

import com.example.cbox.enumeration.FileAccessType;
import org.springframework.web.multipart.MultipartFile;

public record FileCreateEditDto(
        MultipartFile file,
        FileAccessType accessType
){
}
