package com.jwt.dao;

import org.springframework.data.repository.CrudRepository;

import com.jwt.model.User;


public interface UserDao extends CrudRepository<User, Integer> {

    User findByUsername(String username);
}
