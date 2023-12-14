package com.ra.service;

import com.ra.entity.User;
import com.ra.repository.UserRepository;
import com.ra.util.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceIMPL implements UserService {
    @Autowired
    private UserRepository userRepository ;

    @Override
    public List<User> findAll() {
        List<User> list = userRepository.findAll() ;
        return list;
    }

    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public User create(User user) throws UserException {
        // kiểm tra có cái email chưa nhé
        if(userRepository.existsByEmail(user.getEmail()) && user.getId() == 0){
            throw new UserException(user.getEmail()+" đã tồn tại");
        }
        return userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {

    }
}
