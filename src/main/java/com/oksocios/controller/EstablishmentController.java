package com.oksocios.controller;

import com.oksocios.model.Establishment;
import com.oksocios.service.EstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Envy on 14/5/2017.
 */
@RestController
public class EstablishmentController {

    @Autowired
    private EstablishmentService establishmentService;

    @RequestMapping(method = RequestMethod.GET, value = "/establishments")
    public List<Establishment> getAllEstablishments(){
        List<Establishment> establishments =establishmentService.getAllEstablishment();
        return establishments;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/establishments")
    public void addEstablishment(@RequestBody Establishment establishment){
        establishmentService.addEstablishment(establishment);
    }
}
