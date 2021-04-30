package com.app.topo.repository;

import com.app.topo.domain.Route;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface RouteRepository extends CrudRepository<Route, Long> {

    @Override
    List<Route> findAll();

    @Override
    Optional<Route> findById(Long id);

    @Override
    Route save(Route route);

    @Override
    void deleteById(Long id);
}
