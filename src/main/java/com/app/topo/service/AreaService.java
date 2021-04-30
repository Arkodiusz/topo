package com.app.topo.service;

import com.app.topo.domain.Area;
import com.app.topo.repository.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository areaRepository;

    public List<Area> getAllAreas() {
        return areaRepository.findAll();
    }

    public Optional<Area> getArea(final Long id) {
        return areaRepository.findById(id);
    }

    public Area saveArea(final Area area) {
        return areaRepository.save(area);
    }

    public void deleteArea(final Long id) {
        areaRepository.deleteById(id);
    }
}
