package com.example.PirateService;

import com.example.PirateService.models.Pirate;
import com.example.PirateService.models.Raid;
import com.example.PirateService.models.Ship;
import com.example.PirateService.repositories.PirateRepository;
import com.example.PirateService.repositories.RaidRepository;
import com.example.PirateService.repositories.ShipRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PirateServiceApplicationTests {
    @Autowired
    PirateRepository pirateRepository;
    @Autowired
    ShipRepository shipRepository;
    @Autowired
    RaidRepository raidRepository;
    @Test
    public void contextLoads() {
    }
    @Test
    public void createPirateAndShip(){
        Ship ship = new Ship("The Flying Dutchman");
        shipRepository.save(ship);
        Pirate pirate1 = new Pirate("Jack", "Sparrow", 32, ship);
        pirateRepository.save(pirate1);
    }
    @Test
    public void addPiratesAndRaids(){
        Ship ship = new Ship("The Flying Dutchman");
        shipRepository.save(ship);
        Pirate pirate1 = new Pirate("Jack", "Sparrow", 32, ship);
        pirateRepository.save(pirate1);
        Raid raid1 = new Raid("Tortuga", 100);
        raidRepository.save(raid1);
        raid1.addPirate(pirate1);
        raidRepository.save(raid1);
    }
    @Test
    public void canFindPiratesOver50(){
        List<Pirate> found = pirateRepository.findByAgeGreaterThan(50);
        assertEquals(2, found.size());
    }
    @Test
    public void findRaidByLocation(){
        List<Raid> found = raidRepository.findRaidByLocation("Tortuga");
        assertEquals("Tortuga", found.get(0).getLocation());
    }

    @Test
    public void findPirateByRaidId() {
        List<Pirate> foundPirates = pirateRepository.findPirateByRaidsId(3L);
        assertEquals(2, foundPirates.size());
        assertEquals("Pugwash", foundPirates.get(0).getLastName());
    }

    @Test
    public void canFindShipsWithPiratesName(){
        List<Ship> foundShips = shipRepository.findByPiratesFirstName("Jack");
        assertEquals("The Black Pearl", foundShips.get(0).getName());
    }

    @Test
    public void canFindAllRaidsForShip(){
        Ship foundShip = shipRepository.getOne(3L);
        List<Raid> foundRaids = raidRepository.findRaidByPiratesShip(foundShip);
        assertEquals(2, foundRaids.size());
    }
}
