package com.shopproject.shopdigger.security.provider;

import com.shopproject.shopdigger.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DatabaseAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    private UserService userService;


    @Autowired
    public DatabaseAuthenticationProvider(@Qualifier("userInfoServiceImpl") UserDetailsService userDetailsService, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }



    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {


        String login = authentication.getName();
        String password = DigestUtils.md5Hex(authentication.getCredentials().toString());


        if (userService.authenticate(login, password)) {
            UserDetails user = userDetailsService.loadUserByUsername(login);
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        } else {
            return null;
        }

    }

    @Override
    public boolean supports(Class < ? > authentication){
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);


    }}

