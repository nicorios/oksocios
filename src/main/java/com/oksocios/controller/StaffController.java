package com.oksocios.controller;

import com.oksocios.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class StaffController {

    private final UserService userService;

    @Autowired
    public StaffController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/staff")
    private String getStaffView(Model model, @SessionAttribute Long idEstablishment){
        model.addAttribute("staffPeople", userService.getStaff(idEstablishment));
        return "staff";
    }
}
