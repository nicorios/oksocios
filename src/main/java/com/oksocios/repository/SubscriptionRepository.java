package com.oksocios.repository;

import com.oksocios.model.Subscription;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Envy on 21/5/2017.
 */
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
    List<Subscription> findByEstablishment_Id(Long idEstablishment);
    List<Subscription> findByUser_Id(Long idEstablishment);
}
