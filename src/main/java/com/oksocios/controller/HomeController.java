package com.oksocios.controller;

import com.oksocios.model.Entry;
import com.oksocios.service.EntryService;
import com.oksocios.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    private final UserService userService;
    private final EntryService entryService;

    @Autowired
    public HomeController(UserService userService, EntryService entryService){
        this.userService = userService;
        this.entryService = entryService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String loginUser() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/home")
    public String getCurrentUsers(Model model, @RequestParam(required = false, name = "establishment") Long idEstablishment){
        model.addAttribute("entries", entryService.getAllEntriesByEstablishmentIdInLastTwoHours(idEstablishment));
        return "home";
    }
}