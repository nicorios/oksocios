package com.oksocios.service;

import com.oksocios.model.Activity;
import com.oksocios.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> getAllActivities(){
        List<Activity> activities = new ArrayList<>();
        activityRepository.findAll().forEach(activities :: add);
        return activities;
    }

    public Map<String, Object> addActivity(Activity activity){
        Map<String, Object> map = new HashMap<>();
        map.put("update", activity.getId() != null);
        map.put("activity", activityRepository.save(activity));
        return map;
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
