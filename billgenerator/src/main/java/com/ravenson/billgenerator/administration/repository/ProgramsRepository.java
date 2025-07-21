package com.ravenson.billgenerator.administration.repository;

import com.ravenson.billgenerator.administration.model.Programs;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProgramsRepository extends CrudRepository<Programs, Integer> {
    Optional<Programs> findByName(String name);
}
