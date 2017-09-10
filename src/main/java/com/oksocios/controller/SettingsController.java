package com.oksocios.controller;

import com.oksocios.model.Activity;
import com.oksocios.model.Concept;
import com.oksocios.service.ActivityService;
import com.oksocios.service.ConceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class SettingsController {

    private final ActivityService activityService;
    private final ConceptService conceptService;


    @Autowired
    public SettingsController(ActivityService activityService, ConceptService conceptService){
        this.activityService = activityService;
        this.conceptService = conceptService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/settings")
    public String getSettings(Model model, @SessionAttribute Long idEstablishment){
        model.addAttribute("activities", activityService.getAllActivitiesByEstablishment(idEstablishment));
        model.addAttribute("activity", new Activity());
        model.addAttribute("concepts", conceptService.getConceptsByEstablishmentId(idEstablishment));
        model.addAttribute("concept", new Concept());
        return "settings";
    }

}
