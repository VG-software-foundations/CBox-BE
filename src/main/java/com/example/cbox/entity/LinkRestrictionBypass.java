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
    Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "file_id")
    FileEntity fileId;


    @JoinColumn(nullable = false, name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    User userId;

    public void setUser(User user) {
        this.userId = user;
        userId.getRestrictionsBypass().add(this);
    }

    public void setFile(FileEntity file) {
        this.fileId = file;
        file.getRestrictionsBypass().add(this);
    }
}
