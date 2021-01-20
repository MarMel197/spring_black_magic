package com.example.PirateService.repositories;

import com.example.PirateService.models.Pirate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PirateRepository extends JpaRepository<Pirate, Long> {
    List<Pirate> findByAgeGreaterThan(int age);

    List<Pirate> findPirateByRaidsId(long id);
//find read count or get
}
