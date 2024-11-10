package com.example.cbox.repository;

import com.example.cbox.IntegrationTest;
import com.example.cbox.IntegrationTestBase;
import com.example.cbox.entity.User;
import com.example.cbox.enumeration.Role;
import com.example.cbox.enumeration.UserStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@IntegrationTest
@ActiveProfiles("test")
public class UserRepositoryTest extends IntegrationTestBase {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findAll() {
        List<User> all = userRepository.findAll();

        assertNotNull(all);

        assertFalse(all.isEmpty());

    }


    @ParameterizedTest
    @ValueSource(strings = {"8ca8d838-9072-4721-8fcc-1d58c9aa5ce7", "396e17be-aa8f-46cf-b365-dc351e2be659",
            "4784470c-2e19-490f-ad22-79003eecb088", "52831983-f37c-48fd-a551-14ca1df4cb67", "ff54e9c5-16ba-4f08-800e-f6fe4ebf363d",
            "523cad5d-d930-4ca9-8eab-5d5758588b91", "f9a27af1-0bea-4f00-a61c-cd63483e88b6", "de7c61ba-cc9c-404d-bf13-035ee10d19d1"})
    void findById(UUID id) {
        Optional<User> user = userRepository.findById(id);

        assertTrue(user.isPresent());
    }

    @ParameterizedTest
    @ValueSource(strings = {"8ca8d838-9072-4721-8fcc-1d58c9aa5ce7", "396e17be-aa8f-46cf-b365-dc351e2be659",
            "4784470c-2e19-490f-ad22-79003eecb088", "52831983-f37c-48fd-a551-14ca1df4cb67", "ff54e9c5-16ba-4f08-800e-f6fe4ebf363d",
            "523cad5d-d930-4ca9-8eab-5d5758588b91", "f9a27af1-0bea-4f00-a61c-cd63483e88b6", "de7c61ba-cc9c-404d-bf13-035ee10d19d1"})
    void save(UUID id) {
        User test = new User(id, "abib", "asad", UserStatus.ACTIVE, Role.USER, null);

        var result = userRepository.save(test);

        assertNotNull(result);
    }

}
