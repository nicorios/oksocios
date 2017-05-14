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

    @Autowired
    private EstablishmentRepository establishmentRepository;

    public List<Establishment> getAllEstablishment(){
        List<Establishment> establishments = new ArrayList<>();
        establishmentRepository.findAll().forEach(establishments :: add);
        return establishments;
    }

    public void addEstablishment(Establishment establishment){
        establishmentRepository.save(establishment);
    }
}
