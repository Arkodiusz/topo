package com.app.topo.repository;

import com.app.topo.domain.Rock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface RockRepository extends CrudRepository<Rock, Long> {

    @Override
    List<Rock> findAll();

    @Override
    Optional<Rock> findById(Long id);

    @Override
    Rock save(Rock rock);

    @Override
    void deleteById(Long id);
}
