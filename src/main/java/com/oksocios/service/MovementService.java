package com.oksocios.service;

import com.oksocios.model.*;
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

    public List<Movement> getMovementsByConceptId(Long conceptId){
        return movementRepository.findAllByConceptId(conceptId);
    }

    public Movement saveMovementFromSubscription(Subscription subscription){
        Movement movement = new Movement(
                true, subscription.getPrice(), subscription.getUser(), new Concept(1L),
                String.format("Suscripción del socio %s %s", subscription.getUser().getName(), subscription.getUser().getLastName()),
                subscription.getEstablishment(),
                subscription.getSubscriptionDate());

        return movementRepository.save(movement);
    }

    public Boolean deleteMovement(Long id) {
        try{
            movementRepository.delete(id);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public Movement getMovement(Long id){
        return movementRepository.findOne(id);
    }
}
