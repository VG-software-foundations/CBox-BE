package com.example.cbox.service;

import com.example.cbox.annotation.TransactionalService;
import com.example.cbox.dto.create.FileCreateEditDto;
import com.example.cbox.dto.create.UserAuthDto;
import com.example.cbox.dto.read.FileReadDto;
import com.example.cbox.entity.File;
import com.example.cbox.entity.LinkRestrictionBypass;
import com.example.cbox.entity.User;
import com.example.cbox.mapper.FileMapper;
import com.example.cbox.repository.FileRepository;
import com.example.cbox.repository.LinkRepository;
import com.example.cbox.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

@TransactionalService
@RequiredArgsConstructor
public class FileService {
    @Value("./files")
    String bucket = "./files";

    private final FileMapper fileMapper;
    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    private final LinkRepository linkRepository;

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

    @Transactional
    public boolean deleteByLink(UserAuthDto dto, String fileName) {
        String path = bucket + "/" + dto.id().toString() + "/" + fileName;
        var file = fileRepository.findByLink(path);
        fileRepository.deleteByLink(bucket + "/" + fileName);
        return file.isPresent();
    }

    @SneakyThrows
    @Transactional
    public FileReadDto create(UserAuthDto dto, FileCreateEditDto fileDto) {
        Path path = Path.of(bucket, dto.id().toString(), fileDto.file().getOriginalFilename());
        File curFile = new File();
        curFile.setLink(bucket + "/" + dto.id() + "/" + fileDto.file().getOriginalFilename());
        curFile.setCreatedBy(dto.id().toString());
        curFile.setModifiedBy(dto.id().toString());
        curFile.setCreatedAt(Instant.now());
        curFile.setModifiedAt(Instant.now());
        User userEntity = userRepository.findById(dto.id()).get();
        LinkRestrictionBypass link = new LinkRestrictionBypass();
        link.setUser(userEntity);
        link.setFile(curFile);
        fileRepository.save(curFile);
        linkRepository.save(link);
        var inputStream = fileDto.file().getInputStream();
        try (inputStream) {
            Files.createDirectories(path.getParent());
            Files.write(path, inputStream.readAllBytes(), CREATE, TRUNCATE_EXISTING);
        }
        return Optional.of(curFile)
                .map(fileMapper::toFileReadDto)
                .orElseThrow();
    }

    @Transactional
    @SneakyThrows
    public Optional<FileReadDto> update(FileCreateEditDto dto) {
        Path path = Path.of(bucket, dto.file().getOriginalFilename());
        var inputStream = dto.file().getInputStream();
        try (inputStream) {
            Files.write(path, inputStream.readAllBytes(), CREATE, TRUNCATE_EXISTING);
        }
        return fileRepository.findByLink(dto.file().getName())
                .map(file -> {
                    fileMapper.map(file, dto);
                    return file;
                })
                .map(fileRepository::saveAndFlush)
                .map(fileMapper::toFileReadDto);
    }

    public List<FileReadDto> findAllUserFiles(UserAuthDto user) {
        var optionalUser = userRepository.findById(user.id());
        var restrictionsBypass = optionalUser.get().getRestrictions();
        return restrictionsBypass.stream()
                .map(restriction -> fileMapper.toFileReadDto(restriction.getFileId()))
                .toList();
    }

    public Optional<FileReadDto> findFileByLink(String link) {
        return fileRepository.findByLink(link)
                .map(fileMapper::toFileReadDto);
    }

}
