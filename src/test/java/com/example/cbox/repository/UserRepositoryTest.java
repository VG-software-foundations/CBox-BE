package com.example.cbox.repository;

import com.example.cbox.IntegrationTest;
import com.example.cbox.IntegrationTestBase;
import com.example.cbox.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

@IntegrationTest
public class UserRepositoryTest extends IntegrationTestBase {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findById() {
        Optional<User> user = userRepository.findById(UUID.fromString("8ca8d838-9072-4721-8fcc-1d58c9aa5ce7"));
        Assertions.assertTrue(user.isPresent());
    }
}
