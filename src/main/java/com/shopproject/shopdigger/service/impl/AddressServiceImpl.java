package com.shopproject.shopdigger.service.impl;

import com.shopproject.shopdigger.dao.AddressRepository;
import com.shopproject.shopdigger.model.Address;
import com.shopproject.shopdigger.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;


    @Override
    public void saveAdress(Address address) {
      addressRepository.save(address);
    }

    @Override
    public Address findById(Long id) {
        return addressRepository.findAddressById(id);
    }
}
