package com.oksocios.controller;

import com.oksocios.model.Subscription;
import com.oksocios.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Envy on 21/5/2017.
 */
@RestController
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id_user}/subscriptions")
    public List<Subscription> getAllSubscriptionsByUserId(@PathVariable Long id_user){
        return subscriptionService.getAllSubscriptionsByUserId(id_user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "establishments/{id_establishment}/subscriptions")
    public List<Subscription> getAllSubscriptionsByEstablishmentId(@PathVariable Long id_establishment){
        return subscriptionService.getAllSubscriptionsByEstablishmentId(id_establishment);
    }
}
