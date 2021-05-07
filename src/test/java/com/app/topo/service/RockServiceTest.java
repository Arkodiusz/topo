package com.app.topo.service;

import com.app.topo.domain.Rock;
import com.app.topo.repository.RockRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class RockServiceTest {

    @Autowired
    RockService rockService;

    @Autowired
    RockRepository rockRepository;

    @Test
    void testGetAllRocks() {
        //Given
        Rock rock1 = new Rock("r1");
        Rock rock2 = new Rock("r2");

        //When
        rockRepository.save(rock1);
        rockRepository.save(rock2);
        Long id1 = rock1.getId();
        Long id2 = rock2.getId();

        //Then
        assertEquals(2, rockService.getAllRocks().size());
        assertTrue(rockRepository.findById(id1).isPresent());
        assertTrue(rockRepository.findById(id2).isPresent());

        //CleanUp
        rockRepository.deleteById(id1);
        rockRepository.deleteById(id2);
    }

    @Test
    void testGetRock() {
        //Given
        Rock rock = new Rock("");

        //When
        rockRepository.save(rock);
        Long id = rock.getId();

        //Then
        assertTrue(rockService.getRock(id).isPresent());

        //CleanUp
        rockRepository.deleteById(id);
    }

    @Test
    void testSaveRock() {
        //Given
        Rock rock = new Rock("");

        //When
        rockService.saveRock(rock);
        Long id = rock.getId();

        //Then
        assertTrue(rockRepository.findById(id).isPresent());

        //CleanUp
        rockRepository.deleteById(id);

    }

    @Test
    void testDeleteRock() {
        //Given
        Rock rock = new Rock("");

        //When //Then
        rockRepository.save(rock);
        Long id = rock.getId();

        assertTrue(rockRepository.findById(id).isPresent());

        rockService.deleteRock(id);

        assertFalse(rockRepository.findById(id).isPresent());

        //CleanUp
       if (rockRepository.findById(id).isPresent()) {
           rockRepository.deleteById(id);
       }
    }
}