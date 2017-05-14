package com.oksocios.controller;

import com.oksocios.model.Activity;
import com.oksocios.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Created by Envy on 14/5/2017.
 */
@RestController
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping(method = RequestMethod.GET, value = "/activities")
    public List<Activity> getAllActivities(@RequestParam(value = "id_establishment", required = false) Long id_establishment){
        if(Objects.isNull(id_establishment)) return activityService.getAllActivities();
        else return activityService.getAllActivitiesByEstablishment(id_establishment);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/activities")
    public void addActivity(@RequestBody Activity activity){
        activityService.addActivity(activity);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/activities/{id}")
    public Activity getActivity(@PathVariable Long id){
        return activityService.getActivity(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/activities/{id}")
    public void updateActivity(@RequestBody Activity activity){
        activityService.updateActivity(activity);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/activities/{id}")
    public void deleteActivity(@PathVariable Long id){
        activityService.deleteActivity(id);
    }
}
