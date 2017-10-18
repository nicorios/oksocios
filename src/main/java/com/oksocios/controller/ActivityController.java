package com.oksocios.controller;

import com.oksocios.model.Activity;
import com.oksocios.model.Establishment;
import com.oksocios.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping(method = RequestMethod.GET, value = "/activities")
    public List<Activity> getAllActivities(@RequestParam(value = "id_establishment", required = false) Long id_establishment){
        if(Objects.isNull(id_establishment)) return activityService.getAllActivities();
        else return activityService.getAllActivitiesByEstablishment(id_establishment);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/activities")
    public String addActivity(@ModelAttribute Activity activity, @SessionAttribute Long idEstablishment){
        activity.setEstablishment(new Establishment(idEstablishment));
        activityService.addActivity(activity);
        return "redirect:/settings";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/activities/{id}")
    public Activity getActivity(@PathVariable Long id){
        return activityService.getActivity(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/activities/{id}")
    public String updateActivity(@ModelAttribute Activity activity, @SessionAttribute Long idEstablishment){
        activity.setEstablishment(new Establishment(idEstablishment));
        activityService.updateActivity(activity);
        return "redirect:/settings";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/activities/{id}")
    public ResponseEntity<Boolean> deleteActivity(@PathVariable Long id){
        try{
            activityService.deleteActivity(id);
        }catch(Exception e){
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
