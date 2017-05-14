package com.oksocios.controller;

import com.oksocios.model.Establishment;
import com.oksocios.service.EstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.GET, value = "/establishments/{id}")
    public Establishment getEstablishment(@PathVariable Long id){
        return establishmentService.getEstablishment(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/establishments/{id}")
    public void updateEstablishment(@RequestBody Establishment establishment, @PathVariable Long id){
        establishment.setId(id);
        establishmentService.updateEstablishment(establishment);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/establishments/{id}")
    public void deleteEstablishment(@PathVariable Long id){
        establishmentService.deleteEstablishment(id);
    }
}
