package com.oksocios.controller;

import com.oksocios.service.ActivityService;
import com.oksocios.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StatsController {

    private final ActivityService activityService;
    private final SubscriptionService subscriptionService;

    @Autowired
    public StatsController(ActivityService activityService, SubscriptionService subscriptionService){
        this.activityService = activityService;
        this.subscriptionService = subscriptionService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/stats/activities")
    public String getActivitiesStats(@SessionAttribute Long idEstablishment, Model model){
        model.addAttribute("activities", activityService.getAllActivitiesByEstablishment(idEstablishment));
        return "activities-stats";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/stats/activities/{idActivity}/draw")
    @ResponseBody
    public ResponseEntity<int[]> getSubscriptionsPerActivity(@PathVariable Long idActivity, @SessionAttribute Long idEstablishment){
        int[] users = subscriptionService.findAllLastYearSubscriptionsByActivity(idActivity, idEstablishment);
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/stats/customers")
    public String getCustomersStats(){
        return "customers-stats";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/stats/customers/draw")
    @ResponseBody
    public ResponseEntity<int[]> getCustomersAges(@SessionAttribute Long idEstablishment){
        int[] users = subscriptionService.getCustomersAges(idEstablishment);
        return new ResponseEntity(users, HttpStatus.OK);
    }
}
