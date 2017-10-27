package com.oksocios.controller;

import com.oksocios.exceptions.ObjectAlreadyExistsException;
import com.oksocios.exceptions.ObjectNotAccesibleException;
import com.oksocios.model.*;
import com.oksocios.service.*;
import com.oksocios.utils.Constants;
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

    @Autowired
    public CustomerController(SubscriptionService subscriptionService, ActivityService activityService, UserService userService, EntryService entryService){
        this.subscriptionService = subscriptionService;
        this.activityService = activityService;
        this.userService = userService;
        this.entryService = entryService;
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
    public ResponseEntity<?> addCustomer(@RequestBody User user, @SessionAttribute Long idEstablishment) throws ObjectAlreadyExistsException {
        if ((user.getDni() == null) || (user.getEmail() == null)){
            return new ResponseEntity<>("Por favor, Ingrese el DNI e Email del nuevo socio", HttpStatus.OK) ;
        }
        try{
            userService.addUser(user, Constants.ROLE_KEY_CUSTOMER, idEstablishment);
        }catch(ObjectAlreadyExistsException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK) ;
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customers/{id}")
    public String getCustomer(Model model, @PathVariable Long id, @SessionAttribute Long idEstablishment) throws ObjectNotAccesibleException {
        if(!userService.checkMembership(id, idEstablishment)){
            throw new ObjectNotAccesibleException("No está autorizado a ver dicho perfil");
        }
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
        return "customer";
    }
}
