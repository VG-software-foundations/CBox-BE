package com.example.cbox.repository;

import com.example.cbox.IntegrationTest;
import com.example.cbox.IntegrationTestBase;
import com.example.cbox.entity.File;
import com.example.cbox.enumeration.FileAccessType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@IntegrationTest
@ActiveProfiles("test")
public class FileRepositoryTest extends IntegrationTestBase {

    @Autowired
    private FileRepository fileRepository;

    @Test
    void findAll() {
        var all = fileRepository.findAll();

        assertNotNull(all);

        assertFalse(all.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 2})
    void findById(Long id) {
        var file = fileRepository.findById(id);

        assertTrue(file.isPresent());
    }

    @ParameterizedTest
    @ValueSource(strings = {"privet.txt", "hi", "Hallo"})
    void save(String link) {
        File fileEntity = new File();

        fileEntity.setLink(link);
        fileEntity.setAccessType(FileAccessType.PUBLIC);
        fileEntity.setRestrictions(null);
        fileEntity.setCreatedAt(Instant.now());
        fileEntity.setModifiedAt(Instant.now());
        fileEntity.setCreatedBy("admin");
        fileEntity.setModifiedBy("admin");

        var save = fileRepository.save(fileEntity);

        assertNotNull(save);
    }

}
