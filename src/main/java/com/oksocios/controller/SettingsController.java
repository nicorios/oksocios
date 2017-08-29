package com.oksocios.controller;

import com.oksocios.model.Activity;
import com.oksocios.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class SettingsController {

    private final ActivityService activityService;

    @Autowired
    public SettingsController(ActivityService activityService){
        this.activityService = activityService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/settings")
    public String getSettings(Model model, @SessionAttribute Long idEstablishment){
        model.addAttribute("activities", activityService.getAllActivitiesByEstablishment(idEstablishment));
        model.addAttribute("activity", new Activity());
        return "settings";
    }

}
