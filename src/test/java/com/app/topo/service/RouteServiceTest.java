package com.app.topo.service;

import com.app.topo.domain.Route;
import com.app.topo.repository.RouteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class RouteServiceTest {

    @Autowired
    RouteService routeService;

    @Autowired
    RouteRepository routeRepository;

    @Test
    void testGetAllRoutes() {
        //Given
        Route route1 = new Route("r1", "VI");
        Route route2 = new Route("r2", "V+");

        //When
        routeRepository.save(route1);
        routeRepository.save(route2);
        Long id1 = route1.getId();
        Long id2 = route2.getId();

        //Then
        assertEquals(2, routeService.getAllRoutes().size());
        assertTrue(routeRepository.findById(id1).isPresent());
        assertTrue(routeRepository.findById(id2).isPresent());

        //CleanUp
        routeRepository.deleteById(id1);
        routeRepository.deleteById(id2);
    }

    @Test
    void testGetRoute() {
        //Given
        Route route1 = new Route("r1", "VI");

        //When
        routeRepository.save(route1);
        Long id = route1.getId();

        //Then
        assertTrue(routeService.getRoute(id).isPresent());

        //CleanUp
        routeRepository.deleteById(id);
    }

    @Test
    void testSaveRoute() {
        //Given
        Route route1 = new Route("r1", "VI");

        //When
        routeService.saveRoute(route1);
        Long id = route1.getId();

        //Then
        assertTrue(routeRepository.findById(id).isPresent());

        //CleanUp
        routeRepository.deleteById(id);

    }

    @Test
    void testDeleteRoute() {
        //Given
        Route route1 = new Route("r1", "VI");

        //When //Then
        routeRepository.save(route1);
        Long id = route1.getId();

        assertTrue(routeRepository.findById(id).isPresent());

        routeService.deleteRoute(id);

        assertFalse(routeRepository.findById(id).isPresent());

        //CleanUp
        if (routeRepository.findById(id).isPresent()) {
            routeRepository.deleteById(id);
        }
    }
}