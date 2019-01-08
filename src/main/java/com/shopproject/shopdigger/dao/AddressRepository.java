package com.shopproject.shopdigger.dao;

import com.shopproject.shopdigger.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address,Long> {

    public Address findAddressById(Long id);


}
