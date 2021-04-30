package com.app.topo.repository;

import com.app.topo.domain.Wall;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface WallRepository extends CrudRepository <Wall, Long> {

    @Override
    List<Wall> findAll();

    @Override
    Optional<Wall> findById(Long id);

    @Override
    Wall save(Wall wall);

    @Override
    void deleteById(Long id);
}
