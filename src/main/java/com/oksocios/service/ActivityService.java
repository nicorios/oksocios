package com.oksocios.service;

import com.oksocios.model.Activity;
import com.oksocios.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> getAllActivities(){
        List<Activity> activities = new ArrayList<>();
        activityRepository.findAll().forEach(activities :: add);
        return activities;
    }

    public Activity addActivity(Activity activity){
        return activityRepository.save(activity);
    }

    public Activity getActivity(Long id) {
        return activityRepository.findOne(id);
    }

    public void updateActivity(Activity activity) {
        activityRepository.save(activity);
    }

    public void deleteActivity(Long id) {
        activityRepository.delete(id);
    }

    public List<Activity> getAllActivitiesByEstablishment(Long idEstablishment){
        List<Activity> activities = new ArrayList<>();
        activityRepository.findByEstablishment_Id(idEstablishment).forEach(activities :: add);
        return activities;
    }

}
