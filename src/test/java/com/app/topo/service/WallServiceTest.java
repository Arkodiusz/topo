package com.app.topo.service;

import com.app.topo.domain.Wall;
import com.app.topo.repository.WallRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class WallServiceTest {

    @Autowired
    WallService wallService;

    @Autowired
    WallRepository wallRepository;

    @Test
    void testGetAllWalls() {
        //Given
        Wall wall1 = new Wall("w1");
        Wall wall2 = new Wall("w2");

        //When
        wallRepository.save(wall1);
        wallRepository.save(wall2);
        Long id1 = wall1.getId();
        Long id2 = wall2.getId();

        //Then
        assertEquals(2, wallService.getAllWalls().size());
        assertTrue(wallRepository.findById(id1).isPresent());
        assertTrue(wallRepository.findById(id2).isPresent());

        //CleanUp
        wallRepository.deleteById(id1);
        wallRepository.deleteById(id2);
    }

    @Test
    void testGetWall() {
        //Given
        Wall wall = new Wall("");

        //When
        wallRepository.save(wall);
        Long id = wall.getId();

        //Then
        assertTrue(wallService.getWall(id).isPresent());

        //CleanUp
        wallRepository.deleteById(id);
    }

    @Test
    void testSaveWall() {
        //Given
        Wall wall = new Wall("");

        //When
        wallService.saveWall(wall);
        Long id = wall.getId();

        //Then
        assertTrue(wallRepository.findById(id).isPresent());

        //CleanUp
        wallRepository.deleteById(id);

    }

    @Test
    void testDeleteWall() {
        //Given
        Wall wall = new Wall("");

        //When //Then
        wallRepository.save(wall);
        Long id = wall.getId();

        assertTrue(wallRepository.findById(id).isPresent());

        wallService.deleteWall(id);

        assertFalse(wallRepository.findById(id).isPresent());

        //CleanUp
        if (wallRepository.findById(id).isPresent()) {
            wallRepository.deleteById(id);
        }
    }


}
