package com.app.topo.repository;

import com.app.topo.domain.Area;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface AreaRepository extends CrudRepository <Area, Long> {

    @Override
    List<Area> findAll();

    @Override
    Optional<Area> findById(Long id);

    @Override
    Area save(Area area);

    @Override
    void deleteById(Long id);
}