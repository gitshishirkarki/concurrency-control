package com.shishir.concurrency.repository;

import com.shishir.concurrency.entities.Suite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SuiteRepository extends JpaRepository<Suite, Long> {

    List<Suite> findAllByStatus(String status);

    Optional<Suite> findByIdAndVersion(Long id, Integer version);
}
