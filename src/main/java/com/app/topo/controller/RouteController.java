package com.app.topo.controller;

import com.app.topo.domain.Route;
import com.app.topo.exception.RouteNotFoundException;
import com.app.topo.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.topo.TopoApplication.ROOT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(ROOT)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RouteController {

    private final RouteService routeService;

    @RequestMapping(method = RequestMethod.GET, value = "/routes")
    public List<Route> getRoutes() {
        return routeService.getAllRoutes();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/routes/{id}")
    public Route getRoute(@PathVariable Long id) {
        return routeService.getRoute(id).orElseThrow(RouteNotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/routes", consumes = APPLICATION_JSON_VALUE)
    public void createRoute(@RequestBody Route route) {
        routeService.saveRoute(route);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/routes", consumes = APPLICATION_JSON_VALUE)
    public Route updateRoute(@RequestBody Route route) {
        return routeService.saveRoute(route);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/routes/{id}")
    public void deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
    }

}
