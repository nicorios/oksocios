package com.oksocios.controller;

import com.oksocios.model.Entry;
import com.oksocios.model.Subscription;
import com.oksocios.service.EntryService;
import com.oksocios.service.SubscriptionService;
import com.oksocios.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EntryController {

    private final EntryService entryService;
    private final SubscriptionService subscriptionService;
    private final UserService userService;

    @Autowired
    public EntryController(EntryService entryService, SubscriptionService subscriptionService, UserService userService){
        this.entryService = entryService;
        this.subscriptionService = subscriptionService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "users/{id_user}/entries")
    public List<Entry> getAllEntriesByUserId(@PathVariable Long id_user){
        return entryService.getAllEntriesByUserId(id_user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "establishments/{id_establishment}/entries")
    public List<Entry> getAllEntriesByEstablishmentId(@PathVariable Long id_establishment){
        return entryService.getAllEntriesByEstablishmentId(id_establishment);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/new-entry")
    public ResponseEntity<Boolean> addEntry(@RequestBody Entry entry, @SessionAttribute Long idEstablishment){
        Subscription subscription = subscriptionService.checkSubscription(entry.getUser().getDni(), idEstablishment);
        if(subscription != null){
            entryService.addEntry(entry, idEstablishment, subscription);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

}
