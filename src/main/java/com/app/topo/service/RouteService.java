package com.app.topo.service;

import com.app.topo.domain.Route;
import com.app.topo.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Optional<Route> getRoute(final Long id) {
        return routeRepository.findById(id);
    }

    public Route saveRoute(final Route route) {
        return routeRepository.save(route);
    }

    public void deleteRoute(final Long id) {
        routeRepository.deleteById(id);
    }

}
