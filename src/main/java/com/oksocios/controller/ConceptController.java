package com.oksocios.controller;

import com.oksocios.model.Concept;
import com.oksocios.model.Establishment;
import com.oksocios.service.ConceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ConceptController {

    private final ConceptService conceptService;

    @Autowired
    public ConceptController(ConceptService conceptService) {
        this.conceptService = conceptService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/concepts")
    public String addConcept(@SessionAttribute Long idEstablishment, @ModelAttribute Concept concept){
        concept.setEstablishment(new Establishment(idEstablishment));
        conceptService.saveConcept(concept);
        return "redirect:/settings";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/concepts/{id}")
    public String updateConcept(@ModelAttribute Concept concept){
        conceptService.updateConcept(concept);
        return "redirect:/settings";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/concepts/{id}")
    public ResponseEntity<Boolean> deleteConcept(@PathVariable Long id){
        try{
            conceptService.deleteConcept(id);
        }catch(Exception e){
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
