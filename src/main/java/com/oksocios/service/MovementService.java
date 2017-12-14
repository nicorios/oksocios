package com.oksocios.service;

import com.oksocios.model.*;
import com.oksocios.repository.ConceptRepository;
import com.oksocios.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovementService {

    private final MovementRepository movementRepository;
    private final ConceptRepository conceptRepository;

    @Autowired
    public MovementService(MovementRepository movementRepository, ConceptRepository conceptRepository){
        this.movementRepository = movementRepository;
        this.conceptRepository = conceptRepository;
    }

    public List<Movement> getMovements(Long idEstablishment){
        return movementRepository.findAllByEstablishmentId(idEstablishment);
    }

    public Map<String, Object> addMovement(Movement movement, User user, Establishment establishment) {
        Map<String, Object> response = new HashMap<>();
        movement.setUser(user);
        movement.setEstablishment(establishment);
        movement.setDate(new Date());
        response.put("update", movement.getId() != null);
        Movement movementSaved = movementRepository.save(movement);
        movementSaved.setConcept(conceptRepository.findOne(movement.getConcept().getId()));
        response.put("movement", movementSaved);
        return response;
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
