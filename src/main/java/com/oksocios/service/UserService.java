package com.oksocios.service;

import com.oksocios.exceptions.ObjectAlreadyExistsException;
import com.oksocios.exceptions.ObjectNotAccesibleException;
import com.oksocios.model.User;
import com.oksocios.model.UserRole;
import com.oksocios.model.UserRoleId;
import com.oksocios.repository.UserRepository;
import com.oksocios.repository.UserRoleRepository;
import com.oksocios.utils.Constants;
import com.oksocios.utils.Utils;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.management.BadAttributeValueExpException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final EmailService emailService;

    @Autowired
    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.emailService = emailService;
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users :: add);
        return users;
    }

    @Transactional
    public User addUser(User user, Integer role, Long establishmentId) throws ObjectAlreadyExistsException {
        User userSaved, userResponse;
        try{
            userResponse = userRepository.findByEmailOrDni(user.getEmail(), user.getDni());
        }
        catch(Exception e){
            //This exception is thrown when user's dni and email are from two diferrents users
            //The method findByEmailOrDni returns more than one element
            //Is there any other case????
            throw new ObjectAlreadyExistsException("Ya existe un usuario con el email y dni ingresados");
        }
        if(userResponse != null){
            userSaved = checkUserRole(user, userResponse, role, establishmentId);
        }else{
            userSaved = registerUser(user);
            if(establishmentId != null){
                UserRole ur = new UserRole(new UserRoleId(userSaved, role, establishmentId), Constants.getRoleName(role));
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
        user.setPicture(Constants.getRandomImage());
        user.setRegistryDate(new Date());
        User userSaved = userRepository.save(user);
        return userSaved;
    }

    private User checkUserRole(User user, User userResponse, Integer role, Long establishmentId) throws ObjectAlreadyExistsException {
        UserRole userRole = userRoleRepository.findFirstByIdUserIdAndIdEstablishmentId(userResponse.getId(), establishmentId);
        if(userRole != null){
            throw new ObjectAlreadyExistsException("Ya existe un usuario con el email o dni ingresado");
        }else{
            user.setId(userResponse.getId());
            if(userResponse.getName() == null || userResponse.getName().isEmpty()) userResponse.setName(user.getName());
            if(userResponse.getLastName() == null || userResponse.getLastName().isEmpty()) userResponse.setLastName(user.getLastName());
            if(userResponse.getBirthDate() == null) userResponse.setBirthDate(user.getBirthDate());
            if(userResponse.getStreet() == null || userResponse.getStreet().isEmpty()) userResponse.setStreet(user.getStreet());
            if(userResponse.getNumber() == null) userResponse.setNumber(user.getNumber());
            if(userResponse.getEmail() == null) userResponse.setEmail(user.getEmail());
            if(userResponse.getDni() == null) userResponse.setDni(user.getDni());
            if(userResponse.getGender() == null) userResponse.setGender(user.getGender());
            if(userResponse.getPhoneNumber() == null || userResponse.getPhoneNumber().isEmpty()) userResponse.setPhoneNumber(user.getPhoneNumber());
            if(userResponse.getStatus() == null) userResponse.setStatus(user.getStatus());
            if(userResponse.getPicture() == null) userResponse.setPicture(user.getPicture());
            if(userResponse.getPassword() == null) userResponse.setPassword(user.getPassword());
            if(userResponse.getRegistryDate() == null) userResponse.setRegistryDate(user.getRegistryDate());
            if(userResponse.getLatitude() == null) userResponse.setLatitude(user.getLatitude());
            if(userResponse.getLongitude() == null) userResponse.setLongitude(user.getLongitude());
            User userSaved = userRepository.save(userResponse);

            UserRole ur = new UserRole(new UserRoleId(user, role, establishmentId), Constants.getRoleName(role));
            userRoleRepository.save(ur);

            return userSaved;
        }
    }

    public UserRole createUserRole(Long userId, Integer role, Long establishmentId){
        return userRoleRepository.save(new UserRole(
                new UserRoleId(new User(userId), role, establishmentId),
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

    public UserRole updateUser(User user, Long idEstablishment) throws ObjectNotAccesibleException {
        UserRole userRole = hasAuthorityToEdit(user.getId(), idEstablishment);
        if(userRole.getId().getRoleId() != user.getRole()){
            //Update role for this user;
            UserRole userRoleUpdate = new UserRole(
                    new UserRoleId(new User(user.getId()), user.getRole(), idEstablishment),
                    Constants.getRoleName(user.getRole()),
                    userRole.getDate()
            );
            userRoleRepository.delete(new UserRoleId(new User(user.getId()), userRole.getId().getRoleId(), idEstablishment));
            userRole = userRoleRepository.save(userRoleUpdate);

        }
        userRepository.save(user);
        return userRole;
    }

    public UserRole hasAuthorityToEdit(Long id, Long idEstablishment) throws ObjectNotAccesibleException {
        UserRole userRole = userRoleRepository.findFirstByIdUserIdAndIdEstablishmentId(id, idEstablishment);
        //Role > 0 means that it is Customer or Employee
        if((userRole == null) || (userRole.getId().getRoleId() == 0)){
            throw new ObjectNotAccesibleException("No tiene autorización para acceder a éste registro");
        }
        return userRole;
    }

    public void deleteUser(Long id, Long idEstablishment) throws BadAttributeValueExpException {

        userRoleRepository.delete(new UserRoleId(new User(id), Constants.ROLE_KEY_EMPLOYEE, idEstablishment));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User recoveryPassword(String email) throws MessagingException {
        User user = userRepository.findByEmail(email);
        if(user != null){
            String newPass = Utils.getRandomString(20);
            user.setPassword(newPass);
            userRepository.save(user);
            emailService.mailRecoveryPassword(user);
            return user;
        }
        return null;
    }

    public List<UserRole> getStaff(Long idEstablishment) {
        return userRoleRepository.findAllByIdEstablishmentIdAndIdRoleIdIsIn(
                idEstablishment,
                new ArrayList<>(Arrays.asList(Constants.ROLE_KEY_ADMIN, Constants.ROLE_KEY_EMPLOYEE)));
    }

    public UserRole getStaffById(Long id, Long idEstablishment){
        return userRoleRepository.findFirstByIdUserIdAndIdEstablishmentId(id, idEstablishment);
    }

    public List<UserRole> getUserRoleByUserId(Long userId){
        return userRoleRepository.findAllByIdUserId(userId);
    }

    public void updateRole(Long userId, Long idEstablishment) throws ObjectNotAccesibleException {
        UserRole userRole = userRoleRepository.findFirstByIdUserIdAndIdEstablishmentId(userId, idEstablishment);
        if(userRole == null){
            throw new ObjectNotAccesibleException("No se encuentra autorizado a realizar ésta acción");
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>();
        updatedAuthorities.add(new SimpleGrantedAuthority(userRole.getRol())); //add your role here [e.g., new SimpleGrantedAuthority("ROLE_NEW_ROLE")]
        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}
