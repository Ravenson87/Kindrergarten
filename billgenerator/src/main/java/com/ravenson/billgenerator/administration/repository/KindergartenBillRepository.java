package com.ravenson.billgenerator.administration.repository;

import com.ravenson.billgenerator.administration.model.KindergartenBill;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface KindergartenBillRepository extends CrudRepository<KindergartenBill, Integer> {
    Optional<KindergartenBill> findByBillId(Integer billId);
}
