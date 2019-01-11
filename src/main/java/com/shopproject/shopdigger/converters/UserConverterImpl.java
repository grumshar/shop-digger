package com.shopproject.shopdigger.converters;

import com.shopproject.shopdigger.dto.UserDto;
import com.shopproject.shopdigger.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserConverterImpl implements UserConverter{

private AddressConverter addressConverter;

    @Autowired
    public UserConverterImpl(AddressConverter addressConverter) {
        this.addressConverter = addressConverter;
    }

    @Override
    public User convert(UserDto userDto) {
        User user = new User();
        user.setAddress(addressConverter.addressDtoConverter(userDto.getAddressDto()));
        user.setFirstName(userDto.getFirstName());
        user.setSecondName(userDto.getSecondName());
        user.setLogin(userDto.getLogin());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(DigestUtils.md5Hex(userDto.getPassword()));
        user.setToken(UUID.randomUUID().toString());
        user.setMail(userDto.getMail());
        return user;
    }

    @Override
    public UserDto convertDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setAddress(addressConverter.adressConverte(user.getAddress()));
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setSecondName(user.getSecondName());
        userDto.setLogin(user.getLogin());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setPassword(user.getPassword());
        userDto.setMail(user.getMail());
        userDto.setUserStatus(user.getUserStatus());
        return userDto;
    }
}
