package com.example.cbox.repository;

import com.example.cbox.IntegrationTest;
import com.example.cbox.IntegrationTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

@IntegrationTest
@ActiveProfiles("test")
public class FileRepositoryTest extends IntegrationTestBase {

    private Long FILE_ID = 1L;

    @Autowired
    private FileRepository fileRepository;

    @Test
    void findAll() {
        System.out.println("asfas");
        fileRepository.findById(FILE_ID);

        System.out.println("aba");
    }

}
