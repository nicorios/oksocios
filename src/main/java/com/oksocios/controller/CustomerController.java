package com.oksocios.controller;

import com.oksocios.model.Email;
import com.oksocios.model.Entry;
import com.oksocios.model.Subscription;
import com.oksocios.model.User;
import com.oksocios.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {

    private final SubscriptionService subscriptionService;
    private final ActivityService activityService;
    private final UserService userService;
    private final EntryService entryService;
    private final EmailService emailService;

    @Autowired
    public CustomerController(SubscriptionService subscriptionService, ActivityService activityService, UserService userService, EntryService entryService, EmailService emailService){
        this.subscriptionService = subscriptionService;
        this.activityService = activityService;
        this.userService = userService;
        this.entryService = entryService;
        this.emailService = emailService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customers")
    public String getCustomers(Model model, @SessionAttribute Long idEstablishment){
        model.addAttribute("subscriptions", subscriptionService.getAllSubscriptionsByEstablishmentId(idEstablishment));
        return "subscriptions";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customers/ajax")
    @ResponseBody
    public ResponseEntity<List<User>> getEntriesLastWeek(@SessionAttribute Long idEstablishment){
        List<User> users = subscriptionService.getAllUsersFromSubscriptionsByEstablishmentId(idEstablishment);
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customers/new")
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
    public String getCustomer(Model model, @PathVariable Long id, @SessionAttribute Long idEstablishment){
        User user = userService.getUser(id);
        Subscription subscription = subscriptionService.checkSubscription(user.getDni(), idEstablishment);
        List<Entry> entries = entryService.getAllEntriesByEstablishmentIdInLastMonth(user.getDni(), idEstablishment);
        boolean hasLocation = user.hasLocation();
        model.addAttribute("user", user);
        model.addAttribute("hasLocation", hasLocation);
        model.addAttribute("hasSubscription", subscription != null? true : false);
        model.addAttribute("subscription", subscription);
        model.addAttribute("hasEntries", entries.size()>0? true : false);
        model.addAttribute("entries", entries);
        return "user";
    }

    @RequestMapping(method = RequestMethod.POST, value = "customers/{id}/email")
    public ResponseEntity<?> sendEmail(@PathVariable Long id, @RequestBody Email email){
        emailService.sendMail(email);
        return new ResponseEntity<Object>(true, HttpStatus.OK);
    }
}
