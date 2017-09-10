package com.oksocios.service;

import com.oksocios.model.Establishment;
import com.oksocios.model.Movement;
import com.oksocios.model.User;
import com.oksocios.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public Movement addMovement(Movement movement, User user, Establishment establishment) {
        movement.setUser(user);
        movement.setEstablishment(establishment);
        movement.setDate(new Date());
        return movementRepository.save(movement);
    }
}
