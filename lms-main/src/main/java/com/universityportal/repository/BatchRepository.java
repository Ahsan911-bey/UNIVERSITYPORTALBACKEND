package com.universityportal.repository;

import com.universityportal.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BatchRepository extends JpaRepository<Batch, Long> {

    Optional<Batch> findByNameIgnoreCase(String name);
}


