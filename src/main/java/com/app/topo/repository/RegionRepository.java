package com.app.topo.repository;

import com.app.topo.domain.Region;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface RegionRepository extends CrudRepository <Region, Long> {

    @Override
    List<Region> findAll();

    @Override
    Optional<Region> findById(Long id);

    @Override
    Region save(Region region);

    @Override
    void deleteById(Long id);
}
