package com.ravenson.billgenerator.administration.repository;

import com.ravenson.billgenerator.administration.model.Kindergarten;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface KindergartenRepository extends CrudRepository<Kindergarten, Integer> {

    Optional<Kindergarten> findByPib(Integer pib);
    Boolean existsByPib(Integer pib);
    Boolean existsByAccountNumber(String accountNumber);
    Boolean existsByKindergartenPhone(String kindergartenPhone);
    Optional<Kindergarten> findByAccountNumber(String accountNumber);
    Optional<Kindergarten> findByKindergartenPhone(String phone);

}
