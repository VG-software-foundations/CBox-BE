package com.example.cbox.service;

import com.example.cbox.dto.create.UserCreateEditDto;
import com.example.cbox.dto.create.UserLoginDto;
import com.example.cbox.dto.read.JwtAuthenticationResponse;
import com.example.cbox.dto.read.UserReadDto;
import com.example.cbox.entity.User;
import com.example.cbox.enumeration.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserService userService;
    private final EmailVerificationService emailService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signUp(UserCreateEditDto request) {
        int securityCode = new Random().nextInt(1000, 9999);
        UUID id = userService.create(request, securityCode);

        var user = User.builder()
                .id(id)
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .build();

        emailService.sendSimpleEmail(user.getUsername(), securityCode);
        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signIn(UserLoginDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.username());

        if (!((User) user).getStatus().equals(UserStatus.ACTIVE)) {
            throw new RuntimeException();
        }

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}