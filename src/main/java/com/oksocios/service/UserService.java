package com.oksocios.service;

import com.oksocios.model.User;
import com.oksocios.repository.UserRepository;
import com.oksocios.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
        User userResponse = userRepository.findFirstByDni(user.getDni());
        //Actualizo los campos que no tengo, todos los campos menos DNI que es obligatorio
        if(userResponse != null){
            user.setId(userResponse.getId());
            if(user.getName() == null) user.setName(userResponse.getName());
            if(user.getLastName() == null) user.setLastName(userResponse.getLastName());
            if(user.getBirthDate() == null) user.setBirthDate(userResponse.getBirthDate());
            if(user.getStreet() == null) user.setStreet(userResponse.getStreet());
            if(user.getNumber() == null) user.setNumber(userResponse.getNumber());
            if(user.getEmail() == null) user.setEmail(userResponse.getEmail());
            if(user.getGender() == null) user.setGender(userResponse.getGender());
            if(user.getPhoneNumber() == null) user.setPhoneNumber(userResponse.getPhoneNumber());
            if(user.getStatus() == null) user.setStatus(userResponse.getStatus());
            if(user.getPicture() == null) user.setPicture(userResponse.getPicture());
            if(user.getPassword() == null) user.setPassword(userResponse.getPassword());
            if(user.getRegistryDate() == null) user.setRegistryDate(userResponse.getRegistryDate());
        }else{
            // todo set status 0 y confirm by email
            user.setStatus(Constants.STATUS_KEY_ACTIVE);
            //todo definir tabla rol_establecimiento y agregar aca
            //user.setRol(?????);
            user.setRegistryDate(new Date());
        }
        userRepository.save(user);
    }

    public User addNewUser(User user){
        user.setStatus(Constants.STATUS_KEY_PENDING);
        user.setRegistryDate(new Date());
        return userRepository.save(user);
    }

    public User getUser(Long id){
        return userRepository.findOne(id);
    }

    public User getUserByDni(Long dni){ return userRepository.findFirstByDni(dni);}

    public void updateUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.delete(id);
    }
}
