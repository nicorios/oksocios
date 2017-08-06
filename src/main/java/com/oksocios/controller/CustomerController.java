package com.oksocios.controller;

import com.oksocios.model.Subscription;
import com.oksocios.service.ActivityService;
import com.oksocios.service.SubscriptionService;
import com.oksocios.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class CustomerController {

    private final SubscriptionService subscriptionService;
    private final ActivityService activityService;

    @Autowired
    public CustomerController(SubscriptionService subscriptionService, ActivityService activityService){
        this.subscriptionService = subscriptionService;
        this.activityService = activityService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customers")
    public String getCustomers(Model model, @SessionAttribute Long idEstablishment){
        model.addAttribute("subscriptions", subscriptionService.getAllSubscriptionsByEstablishmentId(idEstablishment));
        return "subscriptions";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/new-customer")
    public String getCustomerView(Model model, @SessionAttribute Long idEstablishment){
        model.addAttribute("subscription", new Subscription());
        model.addAttribute("activities", activityService.getAllActivitiesByEstablishment(idEstablishment));
        return "new-customer";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customers")
    public String addCustomer(Model model, @ModelAttribute Subscription subscription){
        return null;
    }
}
