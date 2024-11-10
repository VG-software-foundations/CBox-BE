package com.example.cbox.repository;

import com.example.cbox.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {
    Optional<File> findByLink(String fileName);

    void deleteByLink(String fileName);
}
