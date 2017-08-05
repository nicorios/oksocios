package com.oksocios.service;

import com.oksocios.model.Establishment;
import com.oksocios.repository.EstablishmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Envy on 14/5/2017.
 */
@Service
public class EstablishmentService {

    private final EstablishmentRepository establishmentRepository;

    @Autowired
    public EstablishmentService(EstablishmentRepository establishmentRepository){
        this.establishmentRepository = establishmentRepository;
    }

    public List<Establishment> getAllEstablishment(){
        List<Establishment> establishments = new ArrayList<>();
        establishmentRepository.findAll().forEach(establishments :: add);
        return establishments;
    }

    public void addEstablishment(Establishment establishment){
        establishmentRepository.save(establishment);
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
