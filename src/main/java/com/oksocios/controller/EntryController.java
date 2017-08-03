package com.oksocios.controller;

import com.oksocios.model.Entry;
import com.oksocios.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EntryController {

    private final EntryService entryService;

    @Autowired
    public EntryController(EntryService entryService){
        this.entryService = entryService;
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
    public ResponseEntity<Boolean> addEntry(@RequestBody Entry entry){
        System.out.println(entry);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
