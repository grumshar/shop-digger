package com.shopproject.shopdigger.service;

import com.shopproject.shopdigger.dto.UserDto;
import com.shopproject.shopdigger.model.User;

public interface UserService {

    public void registerUser(UserDto userDto);

    public boolean activateUser(String login, String token);

    public boolean authenticate(String login, String password);

    public void saveUser(User user);

    public User findById(Long id);
}

