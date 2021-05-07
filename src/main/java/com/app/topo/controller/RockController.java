package com.app.topo.controller;

import com.app.topo.domain.Rock;
import com.app.topo.exception.RockNotFoundException;
import com.app.topo.service.RockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.topo.TopoApplication.root;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(root)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RockController {

    private final RockService rockService;

    @RequestMapping(method = RequestMethod.GET, value = "/rocks")
    public List<Rock> getAreas() {
        return rockService.getAllRocks();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rocks/{id}")
    public Rock getRock(@PathVariable Long id) throws RockNotFoundException {
        return rockService.getRock(id).orElseThrow(RockNotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rocks", consumes = APPLICATION_JSON_VALUE)
    public void createRock(@RequestBody Rock rock) {
        rockService.saveRock(rock);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/rocks", consumes = APPLICATION_JSON_VALUE)
    public Rock updateRock(@RequestBody Rock rock) {
        return rockService.saveRock(rock);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/rocks")
    public void deleteRock(@PathVariable Long id) {
        rockService.deleteRock(id);
    }
}
