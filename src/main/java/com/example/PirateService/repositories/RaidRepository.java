package com.example.PirateService.repositories;

import com.example.PirateService.models.Raid;
import com.example.PirateService.models.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RaidRepository extends JpaRepository<Raid, Long> {
    List<Raid> findRaidByLocation(String location);

    List<Raid> findRaidByPiratesShip(Ship ship);
}
