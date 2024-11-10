package com.example.cbox.controller;

import com.example.cbox.annotation.ValidatedController;
import com.example.cbox.dto.create.FileCreateEditDto;
import com.example.cbox.dto.create.UserAuthDto;
import com.example.cbox.dto.read.FileGetDto;
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
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.*;

@Slf4j
@ValidatedController()
@RequestMapping(value = "/files")
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
    public ResponseEntity<FileReadDto> upload(@RequestParam UserAuthDto dto,
                                              @RequestParam FileCreateEditDto fileDto) {
        return ok().body(fileService.create(dto, fileDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileGetDto> findById(@PathVariable("id") Long id) {
        return fileService.findById(id)
                .map(obj -> ok()
                        .body(obj))
                .orElseGet(notFound()::build);
    }

    @GetMapping("/get")
    public ResponseEntity<PageResponse<FileReadDto>> get(@AuthenticationPrincipal UserAuthDto user,
                                                         @RequestParam(defaultValue = "1") @Min(1) Integer page,
                                                         @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer limit) {
        return ok().body(PageResponse.of(fileService.findAllUserFiles(user, page, limit)));
    }

    @SneakyThrows
    @PutMapping
    public ResponseEntity<FileReadDto> update(@RequestParam FileCreateEditDto fileDto) {
        return fileService.update(fileDto)
                .map(obj -> ok().body(obj))
                .orElseGet(notFound()::build);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam UserAuthDto dto,
                                       @RequestParam String fileName) {
        log.info("delete() for file with id {} called", fileName);
        return fileService.deleteByLink(dto, fileName)
                ? noContent().build()
                : notFound().build();
    }
}
