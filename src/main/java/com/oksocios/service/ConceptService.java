package com.oksocios.service;

import com.oksocios.model.Concept;
import com.oksocios.repository.ConceptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConceptService {

    private final ConceptRepository conceptRepository;

    @Autowired
    public ConceptService(ConceptRepository conceptRepository) {
        this.conceptRepository = conceptRepository;
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

    public void deleteConcept(Long id) {
        conceptRepository.delete(id);
    }
}
