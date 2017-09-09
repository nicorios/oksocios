package com.oksocios.repository;

import com.oksocios.model.Concept;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConceptRepository extends CrudRepository<Concept, Long> {
    List<Concept> findAllByEstablishmentId(Long idEstablishment);
}
