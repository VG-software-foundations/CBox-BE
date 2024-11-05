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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IntegrationTest
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

    @Test
    void findById() {
        var actualResult = userService.findById(USER_ID);

        assertTrue(actualResult.isPresent());
    }

    @Test
    void create() {
//        UserCreateEditDto test = new UserCreateEditDto(UUID.fromString("4ee0c212-9ad6-4141-847f-18fe0a5141e7")
//                , "test@gmail.com", "Test", "Testovich", "Testov", "+375299999999");
//
//        UserReadDto userReadDto = userService.create(test);
    }

    @Test
    void update() {
        UserCreateEditDto test = new UserCreateEditDto(
                "test@gmail.com", "12345123456234567", "Test", "Testovich", "Testov", "+375299999999", Role.USER);

        var result = userService.update(USER_ID, test);

        assertTrue(result.isPresent());
    }

    @Test
    void delete() {
        assertTrue(userService.delete(USER_ID));
    }
}