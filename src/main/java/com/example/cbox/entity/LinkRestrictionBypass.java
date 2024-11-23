package com.example.cbox.entity;

import jakarta.persistence.*;
import lombok.*;

import static lombok.EqualsAndHashCode.Include;

@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@Entity
@Table(name = "link_restrictions_bypass")
public class LinkRestrictionBypass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "file_id")
    private File fileId;


    @JoinColumn(nullable = false, name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User userId;

    public void setUser(User user) {
        this.userId = user;
        userId.getRestrictions().add(this);
    }

    public void setFile(File file) {
        this.fileId = file;
        file.getRestrictions().add(this);
    }
}
