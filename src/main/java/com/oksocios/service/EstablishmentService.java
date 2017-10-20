package com.oksocios.service;

import com.oksocios.model.Establishment;
import com.oksocios.model.UserRole;
import com.oksocios.model.UserRoleId;
import com.oksocios.repository.EstablishmentRepository;
import com.oksocios.repository.UserRoleRepository;
import com.oksocios.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class EstablishmentService {

    private final EstablishmentRepository establishmentRepository;
    private final UserService userService;

    @Autowired
    public EstablishmentService(EstablishmentRepository establishmentRepository, UserService userService){
        this.establishmentRepository = establishmentRepository;
        this.userService = userService;
    }

    public List<Establishment> getAllEstablishment(){
        List<Establishment> establishments = new ArrayList<>();
        establishmentRepository.findAll().forEach(establishments :: add);
        return establishments;
    }

    @Transactional
    public Establishment addEstablishment(Establishment establishment){
        Establishment establishmentResponse =  establishmentRepository.save(establishment);
        userService.createUserRole(
                establishmentResponse.getUser().getId(),
                Constants.ROLE_KEY_ADMIN,
                establishmentResponse.getId());
        return establishmentResponse;
    }

    public Establishment getEstablishmentById(Long id) {
        return establishmentRepository.findOne(id);
    }

    public void updateEstablishment(Establishment establishment) {
        establishmentRepository.save(establishment);
    }

    public void deleteEstablishment(Long id) {
        establishmentRepository.delete(id);
    }

    public List<Establishment> getEstablishmentsByUserId(Long userId){
        return establishmentRepository.findByUserId(userId);
    }
}
