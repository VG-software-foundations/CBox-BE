package com.example.cbox.entity;

import com.example.cbox.enumeration.FileAccessType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static lombok.EqualsAndHashCode.Include;

@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@Entity
@Table(name = "files")
public class FileEntity extends AuditingEntity {

    @Id
    @Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "link")
    private String link;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "acces_type")
    private FileAccessType accessType;

    @OneToMany(mappedBy = "fileId")
    private List<LinkRestrictionBypass> restrictionsBypass;

}
