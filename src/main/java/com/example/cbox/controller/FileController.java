package com.example.cbox.controller;

import com.example.cbox.dto.create.FileCreateEditDto;
import com.example.cbox.dto.create.UserAuthDto;
import com.example.cbox.dto.create.UserData;
import com.example.cbox.dto.read.FileReadDto;
import com.example.cbox.dto.read.PageResponse;
import com.example.cbox.service.FileService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@Slf4j
@RestController("/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PageResponse<FileReadDto>> findAll(@RequestParam(defaultValue = "1") @Min(1) Integer page,
                                                             @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer limit) {
        return ok().body(PageResponse.of(fileService.findAll(page, limit)));
    }

    @SneakyThrows
    @PostMapping("/upload")
    public ResponseEntity<FileReadDto> upload(@RequestPart @Validated UserData data,
                                              @AuthenticationPrincipal UserAuthDto user,
                                              @RequestParam FileCreateEditDto file) {
        InputStream inputStream = new FileInputStream(file.fileName());
        return ok().body(fileService.create(data, user, file, inputStream));
    }

    @GetMapping("/get")
    public ResponseEntity<List<FileReadDto>> get(@RequestPart @Validated UserData data,
                                                 @AuthenticationPrincipal UserAuthDto user) {
        return ok().body(fileService.findAllUserFiles(data, user));
    }

    @SneakyThrows
    @PutMapping()
    public ResponseEntity<FileReadDto> update(@RequestParam FileCreateEditDto file) {
        InputStream inputStream = new FileInputStream(file.fileName());
        return fileService.update(file.id(), file, inputStream)
                .map(obj -> ok().body(obj))
                .orElseGet(notFound()::build);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@AuthenticationPrincipal FileCreateEditDto file) {
        log.info("delete() for file with id {} called", file.id());
        return fileService.delete(file.id())
                ? noContent().build()
                : notFound().build();
    }
}
