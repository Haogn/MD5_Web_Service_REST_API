package com.ra.service;

import com.ra.entity.User;
import com.ra.util.exception.UserException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    List<User> findAll() ;

    User findById(Integer id ) ;
    User create(User user) throws UserException;
    void delete(Integer id) ;
}
