package com.shopproject.shopdigger.service.impl;


import com.shopproject.shopdigger.converters.userConverter;
import com.shopproject.shopdigger.dao.UserRepository;
import com.shopproject.shopdigger.dto.UserDto;
import com.shopproject.shopdigger.model.User;
import com.shopproject.shopdigger.model.enums.UserStatus;
import com.shopproject.shopdigger.service.EmailService;
import com.shopproject.shopdigger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.Locale;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private userConverter userConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private MessageSource messageSource;

    @Override
    public void registerUser(UserDto userDto) {

        User user = userConverter.convert(userDto);
        user.setUserStatus(UserStatus.NEW);
        userRepository.save(user);

        String activationTitle = messageSource.getMessage("mail.activation.title", new Object[] {
                user.getFirstName()}, Locale.getDefault());

        String mailText = messageSource.getMessage("mail.activation.text", new Object[] {
                user.getLogin(), user.getToken()}, Locale.getDefault());

        emailService.sendSimpleMessage(user.getMail(), activationTitle, mailText);
    }

    @Override
    public boolean activateUser(String login, String token) {
        User user = userRepository.findOneByLoginAndToken(login, token);
        if (user!=null&& user.getUserStatus().equals(UserStatus.NEW)) {
            user.setUserStatus(UserStatus.ACTIVATED);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean authenticate(String login, String password) {
        return userRepository.findUserByLoginAndPasswordAndUserStatus(
                login, password, UserStatus.ACTIVATED)!=null? true: false;
    }

}
