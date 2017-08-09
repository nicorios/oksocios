package com.oksocios.controller;

import com.oksocios.model.Subscription;
import com.oksocios.model.User;
import com.oksocios.service.ActivityService;
import com.oksocios.service.SubscriptionService;
import com.oksocios.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerController {

    private final SubscriptionService subscriptionService;
    private final ActivityService activityService;
    private final UserService userService;

    @Autowired
    public CustomerController(SubscriptionService subscriptionService, ActivityService activityService, UserService userService){
        this.subscriptionService = subscriptionService;
        this.activityService = activityService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customers")
    public String getCustomers(Model model, @SessionAttribute Long idEstablishment){
        model.addAttribute("subscriptions", subscriptionService.getAllSubscriptionsByEstablishmentId(idEstablishment));
        return "subscriptions";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/new-customer")
    public String getNewCustomerView(Model model, @SessionAttribute Long idEstablishment){
        model.addAttribute("user", new User());
        model.addAttribute("subscription", new Subscription());
        model.addAttribute("activities", activityService.getAllActivitiesByEstablishment(idEstablishment));
        return "new-customer";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customers")
    public ResponseEntity<?> addCustomer(@RequestBody User user, @SessionAttribute Long idEstablishment){
        if (user.getDni() == null) return new ResponseEntity<>("Por favor, Ingrese el DNI del nuevo socio", HttpStatus.OK) ;
        userService.addUser(user);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customers/{id}")
    public String getCustomer(Model model, @PathVariable Long id){
        model.addAttribute("user", userService.getUser(id));
        return "user";
    }
}