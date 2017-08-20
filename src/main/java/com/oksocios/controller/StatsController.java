package com.oksocios.controller;

import com.oksocios.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class StatsController {

    private final ActivityService activityService;

    @Autowired
    public StatsController(ActivityService activityService){
        this.activityService = activityService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/activities-stats")
    public String getActivitiesStats(@SessionAttribute Long idEstablishment, Model model){
        model.addAttribute("activities", activityService.getAllActivitiesByEstablishment(idEstablishment));
        return "activities-stats";
    }
}
