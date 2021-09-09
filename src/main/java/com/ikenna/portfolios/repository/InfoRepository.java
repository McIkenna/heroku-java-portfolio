package com.ikenna.portfolios.repository;


import com.ikenna.portfolios.infos.Info;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoRepository extends CrudRepository<Info, Long> {
    Info findById(long id);

    @Override
    Iterable<Info> findAll();
}
