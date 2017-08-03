package com.oksocios.controller;


import com.oksocios.model.Establishment;
import com.oksocios.model.User;
import com.oksocios.service.EstablishmentService;
import com.oksocios.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final EstablishmentService establishmentService;

    @Autowired
    public UserController(UserService userService, EstablishmentService establishmentService){
        this.userService = userService;
        this.establishmentService = establishmentService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "users/{id}")
    public void updateUser(@RequestBody User user, @PathVariable Long id){
        user.setId(id);
        userService.updateUser(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "users/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/establishments")
    public String selectEstablishments(Principal principal, Model model){
        User user =(User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        List<Establishment> establishments = establishmentService.getEstablishmentsByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("hasEstablishments", establishments.size()>0? true : false);
        model.addAttribute("establishments", establishments);
        return "establishments";
    }

}
