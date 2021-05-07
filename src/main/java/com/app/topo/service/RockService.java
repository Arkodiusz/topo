package com.app.topo.service;

import com.app.topo.domain.Rock;
import com.app.topo.repository.RockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RockService {

    private final RockRepository rockRepository;

    public List<Rock> getAllRocks() {
        return rockRepository.findAll();
    }

    public Optional<Rock> getRock(final Long id) {
        return rockRepository.findById(id);
    }

    public Rock saveRock(final Rock rock) {
        return rockRepository.save(rock);
    }

    public void deleteRock(final Long id) {
        rockRepository.deleteById(id);
    }
}
