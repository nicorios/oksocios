package com.oksocios.repository;

import com.oksocios.model.Movement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovementRepository extends CrudRepository<Movement, Long> {
    List<Movement> findAllByEstablishmentId(Long idEstablishment);
    List<Movement> findAllByConceptId(Long conceptId);
}
