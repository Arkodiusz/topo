package com.app.topo.repository;

import com.app.topo.domain.Sector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface SectorRepository extends CrudRepository<Sector, Long> {

    @Override
    List<Sector> findAll();

    @Override
    Optional<Sector> findById(Long id);

    @Override
    Sector save(Sector sector);

    @Override
    void deleteById(Long id);
}
