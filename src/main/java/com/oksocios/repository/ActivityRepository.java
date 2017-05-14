package com.oksocios.repository;

import com.oksocios.model.Activity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Envy on 14/5/2017.
 */
public interface ActivityRepository extends CrudRepository<Activity, Long> {
    List<Activity> findByEstablishment_Id(Long idEstablishment);
}
