package com.ravenson.billgenerator.administration.repository;

import com.ravenson.billgenerator.administration.model.ChildProgram;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChildProgramRepository extends CrudRepository<ChildProgram, Integer> {
    Optional<ChildProgram> findByChildIdAndProgramId(Integer childId, Integer programId);
    Boolean existsByChildId(Integer childId);
    @Query(
            name = "ChildProgram.sumAllProgramPricesByChildId",
            nativeQuery = true
    )
    Double sumAllProgramPricesByChildId(@Param("child_id")Integer childId);
}
