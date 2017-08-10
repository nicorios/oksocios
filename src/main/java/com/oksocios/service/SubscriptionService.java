package com.oksocios.service;

import com.oksocios.model.Establishment;
import com.oksocios.model.Subscription;
import com.oksocios.model.User;
import com.oksocios.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final EstablishmentService establishmentService;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, EstablishmentService establishmentService){
        this.subscriptionRepository = subscriptionRepository;
        this.establishmentService = establishmentService;
    }

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

    public Subscription checkSubscription(Long dni, Long currentEstablishment){
        return subscriptionRepository.findFirstByUserDniAndEstablishmentIdAndExpirationDateIsAfter(dni, currentEstablishment, new Date());
    }

    public void addSubscription(Subscription subscription, User user, Long idEstablishment){
        subscription.setEstablishment(new Establishment(idEstablishment));
        subscription.setUser(user);
        subscription.setSubscriptionDate(new Date());

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, subscription.getPeriod());
        subscription.setExpirationDate(cal.getTime());

        if(subscription.getClassesLeft() == null) subscription.setFreePass(true);
        if(subscription.getPrice() == null) subscription.setPrice(0D);
        subscriptionRepository.save(subscription);
    }

    public void updateSubscription(Subscription subscription){
        subscriptionRepository.save(subscription);
    }

    public void deleteSubscription(Long idSubscription){
        subscriptionRepository.delete(idSubscription);
    }

}
