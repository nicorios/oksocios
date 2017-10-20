package com.oksocios.service;

import com.oksocios.exceptions.ObjectAlreadyExistsException;
import com.oksocios.model.User;
import com.oksocios.model.UserRole;
import com.oksocios.model.UserRoleId;
import com.oksocios.repository.UserRepository;
import com.oksocios.repository.UserRoleRepository;
import com.oksocios.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users :: add);
        return users;
    }

    @Transactional
    public User addUser(User user, Integer role, Long establishmentId) throws ObjectAlreadyExistsException {
        User userSaved;
        User userResponse = userRepository.findByEmail(user.getEmail());
        if(userResponse != null){
            userSaved = checkUserRole(user, userResponse, role, establishmentId);
        }else{
            userSaved = registerUser(user, role, establishmentId);
            if(establishmentId != null){
                UserRole ur = new UserRole(new UserRoleId(userSaved.getId(), role, establishmentId), Constants.getRoleName(role));
                userRoleRepository.save(ur);
            }
        }
        return userSaved;
    }

    private User registerUser(User user, Integer role, Long establishmentId){
        // todo set status 0 y confirm by email
        user.setStatus(Constants.STATUS_KEY_ACTIVE);
        user.setRegistryDate(new Date());
        User userSaved = userRepository.save(user);
        return userSaved;
    }

    private User checkUserRole(User user, User userResponse, Integer role, Long establishmentId) throws ObjectAlreadyExistsException {
        UserRole userRole = userRoleRepository.findFirstByIdUserIdAndIdRoleIdAndIdEstablishmentId(userResponse.getId(), role, establishmentId);
        if(userRole != null){
            throw new ObjectAlreadyExistsException("Ya existe un usuario con el email ingresado");
        }else{
            user.setId(userResponse.getId());
            if(user.getName() == null) user.setName(userResponse.getName());
            if(user.getLastName() == null) user.setLastName(userResponse.getLastName());
            if(user.getBirthDate() == null) user.setBirthDate(userResponse.getBirthDate());
            if(user.getStreet() == null) user.setStreet(userResponse.getStreet());
            if(user.getNumber() == null) user.setNumber(userResponse.getNumber());
            if(user.getEmail() == null) user.setEmail(userResponse.getEmail());
            if(user.getDni() == null) user.setDni(userResponse.getDni());
            if(user.getGender() == null) user.setGender(userResponse.getGender());
            if(user.getPhoneNumber() == null) user.setPhoneNumber(userResponse.getPhoneNumber());
            if(user.getStatus() == null) user.setStatus(userResponse.getStatus());
            if(user.getPicture() == null) user.setPicture(userResponse.getPicture());
            if(user.getPassword() == null) user.setPassword(userResponse.getPassword());
            if(user.getRegistryDate() == null) user.setRegistryDate(userResponse.getRegistryDate());
            User userSaved = userRepository.save(user);

            UserRole ur = new UserRole(new UserRoleId(user.getId(), role, establishmentId), Constants.getRoleName(role));
            userRoleRepository.save(ur);

            return userSaved;
        }
    }

    public UserRole createUserRole(Long userId, Integer role, Long establishmentId){
        return userRoleRepository.save(new UserRole(
                new UserRoleId(userId, role, establishmentId),
                Constants.getRoleName(role)
        ));
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

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
