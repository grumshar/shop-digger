package com.shopproject.shopdigger.dao;

import com.shopproject.shopdigger.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
