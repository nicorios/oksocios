package com.oksocios.repository;

import com.oksocios.model.Subscription;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
    List<Subscription> findByEstablishment_Id(Long idEstablishment);
    List<Subscription> findByUser_Id(Long idEstablishment);
    Subscription findFirstByUserDniAndEstablishmentIdAndExpirationDateIsAfter(Long dni, Long establishmentId, Date date);
}
