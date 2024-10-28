package com.example.cbox.entity;

import com.example.cbox.enumeration.Role;
import com.example.cbox.enumeration.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

import static lombok.EqualsAndHashCode.*;

@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends AuditingEntity {
    @Id
    @Include
    private UUID id;

    @Column(nullable = false, unique = true, name = "email")
    private String email;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "second_name")
    private String secondName;

    @Column(nullable = false, name = "username")
    private String username;

    @Column(nullable = false, name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status")
    private UserStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "role")
    private Role role;
}
