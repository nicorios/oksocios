package com.oksocios.service;

import com.oksocios.model.Movement;
import com.oksocios.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementService {

    private final MovementRepository movementRepository;

    @Autowired
    public MovementService(MovementRepository movementRepository){
        this.movementRepository = movementRepository;
    }

    public List<Movement> getMovements(Long idEstablishment){
        return movementRepository.findAllByEstablishmentId(idEstablishment);
    }
}
