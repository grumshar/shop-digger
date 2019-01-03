package com.shopproject.shopdigger.service.impl;

import com.shopproject.shopdigger.converters.userConverter;
import com.shopproject.shopdigger.dao.UserRepository;
import com.shopproject.shopdigger.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserInfoServiceImpl implements UserDetailsService {

    @Autowired
    private com.shopproject.shopdigger.converters.userConverter userConverter;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Objects.requireNonNull(username);
        UserDto userDto = userConverter.convertDto(userRepository.findFirstByLogin(username));
  //              .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return userDto;
    }
}
