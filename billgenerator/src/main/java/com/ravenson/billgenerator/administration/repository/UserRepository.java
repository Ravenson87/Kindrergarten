package com.ravenson.billgenerator.administration.repository;

import com.ravenson.billgenerator.administration.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
