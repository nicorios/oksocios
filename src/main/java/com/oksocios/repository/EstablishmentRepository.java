package com.oksocios.repository;

import com.oksocios.model.Establishment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EstablishmentRepository extends CrudRepository<Establishment, Long> {
    List<Establishment> findByUserId(Long userId);
}
