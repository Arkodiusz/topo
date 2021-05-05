package com.app.topo.controller;

import com.app.topo.domain.Area;
import com.app.topo.exception.AreaNotFoundException;
import com.app.topo.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import static com.app.topo.TopoApplication.root;

@RestController
@RequestMapping(root)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AreaController {

    private final AreaService areaService;

    @RequestMapping(method = RequestMethod.GET, value = "/areas")
    public List<Area> getAreas() {
        return areaService.getAllAreas();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/areas/{areaId}")
    public Area getTask(@PathVariable Long id) throws AreaNotFoundException {
        return areaService.getArea(id).orElseThrow(AreaNotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/areas", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody Area area) {
        areaService.saveArea(area);
    }
}
