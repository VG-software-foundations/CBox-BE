package com.example.cbox.service;

import com.example.cbox.annotation.TransactionalService;
import com.example.cbox.dto.create.FileCreateEditDto;
import com.example.cbox.dto.create.LinkCreateEditDto;
import com.example.cbox.dto.create.UserAuthDto;
import com.example.cbox.dto.create.UserData;
import com.example.cbox.dto.read.FileReadDto;
import com.example.cbox.mapper.FileMapper;
import com.example.cbox.repository.FileRepository;
import com.example.cbox.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE;

@TransactionalService
@RequiredArgsConstructor
public class FileService {
    private final UserRepository userRepository;
    @Value("./files")
    String bucket;

    private final FileMapper fileMapper;
    private final FileRepository fileRepository;
    private final LinkService linkService;

    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<FileReadDto> findAll(Integer page, Integer limit) {
        PageRequest req = PageRequest.of(page - 1, limit);
        return fileRepository.findAll(req)
                .map(fileMapper::toFileReadDto);
    }

    public Optional<FileReadDto> findById(Long id) {
        return fileRepository.findById(id)
                .map(fileMapper::toFileReadDto);
    }

    @Transactional
    public boolean delete(Long id) {
        var file = fileRepository.findById(id);
        fileRepository.deleteById(id);
        return file.isPresent();
    }

    @SneakyThrows
    @Transactional
    public FileReadDto create(UserData data, UserAuthDto user, FileCreateEditDto dto, InputStream inputStream) {
        Path path = Path.of(bucket, dto.fileName());
        try (inputStream) {
            Files.createDirectories(path.getParent());
            Files.write(path, inputStream.readAllBytes(), CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
        linkService.create(new LinkCreateEditDto(user.id(), dto.id()));
        return Optional.of(dto)
                .map(fileMapper::toFileEntity)
                .map(fileRepository::save)
                .map(fileMapper::toFileReadDto)
                .orElseThrow();
    }

    @Transactional
    @SneakyThrows
    public Optional<FileReadDto> update(Long id, FileCreateEditDto dto, InputStream inputStream) {
        Path path = Path.of(bucket, dto.fileName());
        try (inputStream) {
            Files.createDirectories(path.getParent());
            Files.write(path, inputStream.readAllBytes(), CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
        return fileRepository.findById(id)
                .map(file -> {
                    fileMapper.map(file, dto);
                    return file;
                })
                .map(fileRepository::saveAndFlush)
                .map(fileMapper::toFileReadDto);
    }

    public List<FileReadDto> findAllUserFiles(UserData data, UserAuthDto user) {
        var optionalUser = userRepository.findById(user.id());
        var restrictionsBypass = optionalUser.get().getRestrictionsBypass();
        return restrictionsBypass.stream()
                .map(restriction -> fileMapper.toFileReadDto(restriction.getFileId()))
                .toList();
    }

}
