package com.example.cbox.repository;

import com.example.cbox.IntegrationTest;
import com.example.cbox.IntegrationTestBase;
import com.example.cbox.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@IntegrationTest
@ActiveProfiles("test")
public class UserRepositoryTest extends IntegrationTestBase {

    private UUID USER_ID = UUID.fromString("8ca8d838-9072-4721-8fcc-1d58c9aa5ce7");

    @Autowired
    private UserRepository userRepository;

    @Test
    void findAll() {
        List<User> all = userRepository.findAll();

        assertNotNull(all);

        assertFalse(all.isEmpty());

    }


    @Test
    void findById() {
        Optional<User> user = userRepository.findById(USER_ID);

        assertTrue(user.isPresent());
    }

    @Test
    void save() {
//        User test = new User(USER_ID,
//                "test@gmail.com", "Test",
//                "Testovich", "Testov",
//                "+375299999999", UserStatus.ACTIVE);
//
//        var result = userRepository.save(test);
//
//        assertNotNull(result);
    }

}
