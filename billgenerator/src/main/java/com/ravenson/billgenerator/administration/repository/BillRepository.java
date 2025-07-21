package com.ravenson.billgenerator.administration.repository;

import com.ravenson.billgenerator.administration.model.Bill;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill, Integer> {
}
