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
        User userSaved, userResponse;
        userResponse = userRepository.findByEmailOrDni(user.getEmail(), user.getDni());
        if(userResponse != null){
            userSaved = checkUserRole(user, userResponse, role, establishmentId);
        }else{
            userSaved = registerUser(user);
            if(establishmentId != null){
                UserRole ur = new UserRole(new UserRoleId(userSaved.getId(), role, establishmentId), Constants.getRoleName(role));
                userRoleRepository.save(ur);
            }
        }
        return userSaved;
    }

    public User registerAdmin(User user) throws ObjectAlreadyExistsException {
        User userResponse = userRepository.findByEmail(user.getEmail());
        if(userResponse != null){
            UserRole userRole = userRoleRepository.findFirstByIdUserIdAndIdRoleId(userResponse.getId(), Constants.ROLE_KEY_ADMIN);
            if(userRole != null){
                throw new ObjectAlreadyExistsException("Ya existe un admin con el email ingresado");
            }
            return userResponse;
        }
        user.setStatus(Constants.STATUS_KEY_ACTIVE);
        return registerUser(user);
    }

    private User registerUser(User user){
        // todo set status 0 y confirm by email
        user.setStatus(Constants.STATUS_KEY_ACTIVE);
        user.setRegistryDate(new Date());
        User userSaved = userRepository.save(user);
        return userSaved;
    }

    private User checkUserRole(User user, User userResponse, Integer role, Long establishmentId) throws ObjectAlreadyExistsException {
        UserRole userRole = userRoleRepository.findFirstByIdUserIdAndIdRoleIdAndIdEstablishmentId(userResponse.getId(), role, establishmentId);
        if(userRole != null){
            throw new ObjectAlreadyExistsException("Ya existe un usuario con el email o dni ingresado");
        }else{
            user.setId(userResponse.getId());
            if(userResponse.getName() == null) userResponse.setName(user.getName());
            if(userResponse.getLastName() == null) userResponse.setLastName(user.getLastName());
            if(userResponse.getBirthDate() == null) userResponse.setBirthDate(user.getBirthDate());
            if(userResponse.getStreet() == null) userResponse.setStreet(user.getStreet());
            if(userResponse.getNumber() == null) userResponse.setNumber(user.getNumber());
            if(userResponse.getEmail() == null) userResponse.setEmail(user.getEmail());
            if(userResponse.getDni() == null) userResponse.setDni(user.getDni());
            if(userResponse.getGender() == null) userResponse.setGender(user.getGender());
            if(userResponse.getPhoneNumber() == null) userResponse.setPhoneNumber(user.getPhoneNumber());
            if(userResponse.getStatus() == null) userResponse.setStatus(user.getStatus());
            if(userResponse.getPicture() == null) userResponse.setPicture(user.getPicture());
            if(userResponse.getPassword() == null) userResponse.setPassword(user.getPassword());
            if(userResponse.getRegistryDate() == null) userResponse.setRegistryDate(user.getRegistryDate());
            if(userResponse.getLatitude() == null) userResponse.setLatitude(user.getLatitude());
            if(userResponse.getLongitude() == null) userResponse.setLongitude(user.getLongitude());
            User userSaved = userRepository.save(userResponse);

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

    public boolean checkMembership(Long userId, Long establishmentId) {
        UserRole userRole = userRoleRepository.findFirstByIdUserIdAndIdRoleIdAndIdEstablishmentId(userId, Constants.ROLE_KEY_CUSTOMER, establishmentId);
        return userRole != null;
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
