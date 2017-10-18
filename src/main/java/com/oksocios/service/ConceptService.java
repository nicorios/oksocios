package com.oksocios.service;

import com.oksocios.model.Concept;
import com.oksocios.model.Movement;
import com.oksocios.repository.ConceptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConceptService {

    private final ConceptRepository conceptRepository;
    private final MovementService movementService;

    @Autowired
    public ConceptService(ConceptRepository conceptRepository, MovementService movementService) {
        this.conceptRepository = conceptRepository;
        this.movementService = movementService;
    }

    public List<Concept> getConceptsByEstablishmentId(Long idEstablishment){
        return conceptRepository.findAllByEstablishmentId(idEstablishment);
    }

    public Concept saveConcept(Concept concept) {
        return conceptRepository.save(concept);
    }

    public Concept updateConcept(Concept concept) {
        return conceptRepository.save(concept);
    }

    public Boolean deleteConcept(Long id) {
        List<Movement> movements = movementService.getMovementsByConceptId(id);
        if(movements.size() == 0){
            conceptRepository.delete(id);
            return true;
        }
        return false;

    }
}
