package com.oksocios.service;

import com.oksocios.model.Subscription;
import com.oksocios.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Envy on 21/5/2017.
 */
@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<Subscription> getAllSubscriptions(){
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptionRepository.findAll().forEach(subscriptions :: add);
        return subscriptions;
    }

    public List<Subscription> getAllSubscriptionsByEstablishmentId(Long idEstablishment){
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptionRepository.findByEstablishment_Id(idEstablishment).forEach(subscriptions :: add);
        return subscriptions;
    }

    public List<Subscription> getAllSubscriptionsByUserId(Long idUser){
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptionRepository.findByUser_Id(idUser).forEach(subscriptions :: add);
        return subscriptions;
    }

    public void addSubscription(Subscription subscription){
        subscriptionRepository.save(subscription);
    }

    public void updateSubscription(Subscription subscription){
        subscriptionRepository.save(subscription);
    }

    public void deleteSubscription(Long idSubscription){
        subscriptionRepository.delete(idSubscription);
    }
}
