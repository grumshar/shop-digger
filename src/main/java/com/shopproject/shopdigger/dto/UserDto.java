package com.shopproject.shopdigger.dto;

import com.shopproject.shopdigger.model.Address;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {

    private String firstName;

    private String secondName;

    private String phoneNumber;

    private String mail;

    private String password;

    private String matchingPassword;

    private String login;

    private Address address;

    public UserDto(){}

    public UserDto(String firstName, String secondName,String phoneNumber,String mail,String password,String matchingPassword,String login) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
