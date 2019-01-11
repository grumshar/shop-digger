package com.shopproject.shopdigger.converters;

import com.shopproject.shopdigger.dto.AddressDto;
import com.shopproject.shopdigger.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressConverterImpl implements AddressConverter{
    @Override
    public AddressDto adressConverte(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setStreet(address.getStreet());
        addressDto.setHouseNumber(address.getHouseNumber());
        addressDto.setApartmentNumber(address.getApartmentNumber());
        addressDto.setCity(address.getCity());
        addressDto.setPostalCode(address.getPostalCode());

        return addressDto;

    }

    @Override
    public Address addressDtoConverter(AddressDto addressDto) {
        Address address = new Address();
        address.setId(addressDto.getId());
        address.setStreet(addressDto.getStreet());
        address.setHouseNumber(addressDto.getHouseNumber());
        address.setApartmentNumber(addressDto.getApartmentNumber());
        address.setCity(addressDto.getCity());
        address.setPostalCode(addressDto.getPostalCode());

        return address;
    }
}
