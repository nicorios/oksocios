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

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository){
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<Subscription> getAllSubscriptions(){
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptionRepository.findAll().forEach(subscriptions :: add);
        return subscriptions;
    }

    public List<Subscription> getAllSubscriptionsByEstablishmentId(Long idEstablishment){
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptionRepository.findAllByEstablishmentIdAndExpirationDateIsAfter(idEstablishment, new Date()).forEach(subscriptions :: add);
        return subscriptions;
    }

    public List<User> getAllUsersFromSubscriptionsByEstablishmentId(Long idEstablishment){
        List<User> users = new ArrayList<>();
        subscriptionRepository.findAllByEstablishmentIdAndExpirationDateIsAfter(idEstablishment, new Date()).forEach(subscription -> users.add(subscription.getUser()));
        return users;
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

    public int[] findAllLastYearSubscriptionsByActivity(Long idActivity, Long idEstablishment){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        List<Subscription> subscriptions = subscriptionRepository.findAllByActivityIdAndAndEstablishmentIdAndSubscriptionDateIsAfter(idActivity, idEstablishment, cal.getTime());
        return calculateUsersActivitiesStat(subscriptions);
    }

    public int[] calculateUsersActivitiesStat(List<Subscription> subscriptions){
        int[] users = new int[25];
        User user;
        Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH);
        for (Subscription subscription : subscriptions){
            user = subscription.getUser();
            if(user.getGender() != null){
                cal.setTime(subscription.getSubscriptionDate());
                int month = cal.get(Calendar.MONTH);
                int diff = 11 - currentMonth;
                if(month > currentMonth && user.getGender()) users[month-currentMonth-1]++;
                if(month <= currentMonth && user.getGender()) users[month+diff]++;
                if(month > currentMonth && !user.getGender()) users[month-currentMonth-1 +12]++;
                if(month <= currentMonth && !user.getGender()) users[month+diff +12]++;
            }
            else users[24]++;
        }
        return users;
    }

    public int[] getCustomersAges(Long idEstablishment) {
        List<Subscription> subscriptions = subscriptionRepository.findAllByEstablishmentIdAndExpirationDateIsAfter(idEstablishment, new Date());
        return calculateAgesUsersStat(subscriptions);
    }

    public int[] calculateAgesUsersStat(List<Subscription> subscriptions){
        int[] users = new int[54];
        User customer;
        Integer age;
        for(Subscription subscription: subscriptions){
            customer = subscription.getUser();
            age = customer.calculateAge();
            if(subscription.getUser().getGender() != null){
                if(subscription.getUser().getGender()) {
                    if(age>16) users[0]++;
                    if(age<40) users[26]++;
                    if(age >= 16 && age <= 40)users[age-15]++;
                }
                else {
                    if(age>16) users[27]++;
                    if(age<40) users[53]++;
                    if(age >= 16 && age <= 40)users[age+12]++;
                }
            }
            else users[54]++;
        }
        return users;
    }
}
