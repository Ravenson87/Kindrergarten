package com.ravenson.billgenerator.administration.repository;

import com.ravenson.billgenerator.administration.model.ChildProgram;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ChildProgramRepository extends CrudRepository<ChildProgram, Integer> {
    Optional<ChildProgram> findByChildIdAndProgramId(Integer childId, Integer programId);
}
