package com.oksocios.controller;

import com.oksocios.model.Entry;
import com.oksocios.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Envy on 21/5/2017.
 */
@RestController
public class EntryController {

    @Autowired
    private EntryService entryService;

    @RequestMapping(method = RequestMethod.GET, value = "users/{id_user}/entries")
    public List<Entry> getAllEntriesByUserId(@PathVariable Long id_user){
        return entryService.getAllEntriesByUserId(id_user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "establishments/{id_establishment}/entries")
    public List<Entry> getAllEntriesByEstablishmentId(@PathVariable Long id_establishment){
        return entryService.getAllEntriesByEstablishmentId(id_establishment);
    }


}
