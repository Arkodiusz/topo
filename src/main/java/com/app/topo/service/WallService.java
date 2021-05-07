package com.app.topo.service;

import com.app.topo.domain.Wall;
import com.app.topo.repository.WallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WallService {

    private final WallRepository wallRepository;

    public List<Wall> getAllWalls() {
        return wallRepository.findAll();
    }

    public Optional<Wall> getWall(final Long id) {
        return wallRepository.findById(id);
    }

    public Wall saveWall(final Wall wall) {
        return wallRepository.save(wall);
    }

    public void deleteWall(final Long id) {
        wallRepository.deleteById(id);
    }
}
