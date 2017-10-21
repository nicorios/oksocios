package com.oksocios.controller;

import com.oksocios.model.Subscription;
import com.oksocios.model.User;
import com.oksocios.service.SubscriptionService;
import com.oksocios.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final UserService userService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService, UserService userService){
        this.subscriptionService = subscriptionService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id_user}/subscriptions")
    public List<Subscription> getAllSubscriptionsByUserId(@PathVariable Long id_user){
        return subscriptionService.getAllSubscriptionsByUserId(id_user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "establishments/{id_establishment}/subscriptions")
    public List<Subscription> getAllSubscriptionsByEstablishmentId(@PathVariable Long id_establishment){
        return subscriptionService.getAllSubscriptionsByEstablishmentId(id_establishment);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/subscriptions")
    private ResponseEntity<?> addSubscription(@RequestBody Subscription subscription, @SessionAttribute Long idEstablishment){
        if (subscription.getUser().getDni() == null) return new ResponseEntity<>("Por favor, Ingrese el DNI del socio", HttpStatus.OK) ;
        User user = userService.getUserByDni(subscription.getUser().getDni());
        if(user == null) return new ResponseEntity<>("No existe ningún usuario registrado con el DNI ingresado", HttpStatus.OK);
        Subscription subscriptionResponse = subscriptionService.checkSubscription(subscription.getUser().getDni(), idEstablishment);
        if(subscriptionResponse != null) return new ResponseEntity<>("Ya existe una subscripción para el DNI ingresado", HttpStatus.OK);
        subscriptionService.addSubscription(subscription, user, idEstablishment);
        return new ResponseEntity<Object>(true, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/subscriptions/{id}")
    public ResponseEntity<Boolean> deleteSubscription(@PathVariable Long id){
        return new ResponseEntity<>(subscriptionService.deleteSubscription(id), HttpStatus.OK);
    }
}
