package com.app.topo.repository;

import com.app.topo.domain.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {

    @Override
    List<Member> findAll();

    @Override
    Optional<Member> findById(Long id);

    @Override
    Member save(Member user);

    @Override
    void deleteById(Long id);
}
