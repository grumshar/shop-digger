package com.shopproject.shopdigger.converters;

import com.shopproject.shopdigger.dto.AddressDto;
import com.shopproject.shopdigger.model.Address;

public interface AddressConverter {

    public AddressDto adressConverte(Address address);
    public Address addressDtoConverter(AddressDto addressDto);
}
