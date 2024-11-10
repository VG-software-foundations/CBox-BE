package com.example.cbox.service;

import com.example.cbox.IntegrationTest;
import com.example.cbox.IntegrationTestBase;
import com.example.cbox.dto.create.UserCreateEditDto;
import com.example.cbox.dto.read.UserReadDto;
import com.example.cbox.enumeration.Role;
import com.example.cbox.mapper.UserMapper;
import com.example.cbox.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@IntegrationTest
@ActiveProfiles("test")
class UserServiceTest extends IntegrationTestBase {

    private static final UUID USER_ID = UUID.fromString("8ca8d838-9072-4721-8fcc-1d58c9aa5ce7");

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setForService() {
        userService = new UserService(userMapper, userRepository);
    }

    @Test
    void findAll() {
        Page<UserReadDto> users = userService.findAll(1, 10);

        assertThat(users).hasSize(10);
    }

    @ParameterizedTest
    @ValueSource(strings = {"8ca8d838-9072-4721-8fcc-1d58c9aa5ce7", "396e17be-aa8f-46cf-b365-dc351e2be659",
            "4784470c-2e19-490f-ad22-79003eecb088", "52831983-f37c-48fd-a551-14ca1df4cb67", "ff54e9c5-16ba-4f08-800e-f6fe4ebf363d",
            "523cad5d-d930-4ca9-8eab-5d5758588b91", "f9a27af1-0bea-4f00-a61c-cd63483e88b6", "de7c61ba-cc9c-404d-bf13-035ee10d19d1"})
    void findById(UUID id) {
        var actualResult = userService.findById(id);

        assertTrue(actualResult.isPresent());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Nichosi", "Aleg", "Cadrol",
    "ccnc", "Miracle", "Dratute"})
    void create(String username) {
        UserCreateEditDto test = new UserCreateEditDto("asbdishfioi23r3lA", username, Role.USER);

        UserReadDto userReadDto = userService.create(test);

        assertNotNull(userReadDto);
    }

    @ParameterizedTest
    @ValueSource(strings = {"8ca8d838-9072-4721-8fcc-1d58c9aa5ce7", "396e17be-aa8f-46cf-b365-dc351e2be659",
            "4784470c-2e19-490f-ad22-79003eecb088", "52831983-f37c-48fd-a551-14ca1df4cb67", "ff54e9c5-16ba-4f08-800e-f6fe4ebf363d",
            "523cad5d-d930-4ca9-8eab-5d5758588b91", "f9a27af1-0bea-4f00-a61c-cd63483e88b6", "de7c61ba-cc9c-404d-bf13-035ee10d19d1"})
    void update(UUID id) {
        UserCreateEditDto test = new UserCreateEditDto("abib", "Porox", Role.USER);

        var result = userService.update(id, test);

        assertTrue(result.isPresent());
    }

    @Test
    void delete() {
        assertTrue(userService.delete(USER_ID));
    }

    @ParameterizedTest
    @ValueSource(strings = {"702ca765-f9a6-44db-b68e-c478459ff52e", "b09235d2-afbe-45ea-8471-5dd479568f4c",
            "61accca9-5fd5-4545-968a-ef8079661224", "7d67fe1e-b163-4d89-95d4-d2b2ce0404cf", "0f8016b3-f9a9-4f21-8d10-ad472ce4e47d",
            "e6abd863-1ef2-4e26-af09-2fe818aace7f", "ccdbd2e7-8a88-4be4-b917-f2f4e91b12ca", "aca6bfc1-d31e-471c-adbd-1626be42f725"})
    void findByIdFalse(UUID id) {
        var actualResult = userService.findById(id);

        assertFalse(actualResult.isPresent());
    }
}