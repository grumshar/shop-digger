package com.shopproject.shopdigger.service;

import com.shopproject.shopdigger.model.Address;
import org.springframework.stereotype.Service;

public interface AddressService {

    public void saveAdress(Address address);
    public Address findById(Long id);
}
