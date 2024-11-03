package com.example.cbox.repository;

import com.example.cbox.entity.LinkRestrictionBypass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<LinkRestrictionBypass, Long> {
}
