package com.app.topo.controller;

import com.app.topo.domain.Wall;
import com.app.topo.exception.WallNotFoundException;
import com.app.topo.service.WallService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.topo.TopoApplication.ROOT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(ROOT)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class WallController {

    private final WallService wallService;

    @RequestMapping(method = RequestMethod.GET, value = "/walls")
    public List<Wall> getWalls() {
        return wallService.getAllWalls();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/walls/{id}")
    public Wall getWall(@PathVariable Long id) {
        return wallService.getWall(id).orElseThrow(WallNotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/walls", consumes = APPLICATION_JSON_VALUE)
    public void createWall(@RequestBody Wall wall) {
        wallService.saveWall(wall);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/walls", consumes = APPLICATION_JSON_VALUE)
    public Wall updateWall(@RequestBody Wall wall) {
        return wallService.saveWall(wall);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/walls/{id}")
    public void deleteWall(@PathVariable Long id) {
        wallService.deleteWall(id);
    }

}
