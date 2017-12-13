package com.oksocios.controller;

import com.oksocios.model.Concept;
import com.oksocios.model.Establishment;
import com.oksocios.service.ConceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class ConceptController {

    private final ConceptService conceptService;

    @Autowired
    public ConceptController(ConceptService conceptService) {
        this.conceptService = conceptService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/concepts")
    public ResponseEntity<Object> addConcept(@RequestBody Concept concept, @SessionAttribute Long idEstablishment){
        concept.setEstablishment(new Establishment(idEstablishment));
        Map<String, Object> response = conceptService.saveConcept(concept);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/concepts/{id}")
    public String updateConcept(@ModelAttribute Concept concept){
        conceptService.updateConcept(concept);
        return "redirect:/settings";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/concepts/{id}")
    public ResponseEntity<Boolean> deleteConcept(@PathVariable Long id){
        return new ResponseEntity<>(conceptService.deleteConcept(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/concepts/{id}")
    public ResponseEntity<Concept> getConcept(@PathVariable Long id){
        return new ResponseEntity<>(conceptService.getConcept(id), HttpStatus.OK);
    }
}
