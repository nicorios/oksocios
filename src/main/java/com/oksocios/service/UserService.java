package com.oksocios.service;

import com.oksocios.model.User;
import com.oksocios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users :: add);
        return users;
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public User getUser(Long id){
        return userRepository.findOne(id);
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.delete(id);
    }
}
