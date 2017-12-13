package com.oksocios.service;

import com.oksocios.model.Concept;
import com.oksocios.model.Movement;
import com.oksocios.repository.ConceptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Object> saveConcept(Concept concept) {
        Map<String, Object> map = new HashMap<>();
        map.put("update", concept.getId() != null);
        map.put("concept", conceptRepository.save(concept));
        return map;
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

    public Concept getConcept(Long id){
        return conceptRepository.findOne(id);
    }
}
