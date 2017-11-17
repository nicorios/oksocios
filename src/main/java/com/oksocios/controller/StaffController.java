package com.oksocios.controller;

import com.oksocios.exceptions.ObjectAlreadyExistsException;
import com.oksocios.model.User;
import com.oksocios.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Date;

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
        model.addAttribute("user", new User());
        return "staff";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/staff")
    public ResponseEntity<?> addCustomer(@RequestBody User user, @SessionAttribute Long idEstablishment) {
        if ((user.getDni() == null) || (user.getEmail().isEmpty())){
            return new ResponseEntity<>("Por favor, Ingrese el DNI e Email del nuevo socio", HttpStatus.OK) ;
        }
        try{
            userService.addUser(user, user.getRole(), idEstablishment);
        }catch(ObjectAlreadyExistsException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK) ;
        }
        user.setRegistryDate(new Date());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
