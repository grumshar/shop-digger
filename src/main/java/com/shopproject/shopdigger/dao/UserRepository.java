package com.shopproject.shopdigger.dao;

import com.shopproject.shopdigger.model.User;
import com.shopproject.shopdigger.model.enums.UserStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    public User findOneByLoginAndToken(String login, String token);

    public User findUserByLoginAndPasswordAndUserStatus(String login, String password, UserStatus status);

    public User findFirstByLogin(String login);

}
