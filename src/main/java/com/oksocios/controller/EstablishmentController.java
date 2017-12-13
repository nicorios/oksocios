package com.oksocios.controller;

import com.oksocios.model.Establishment;
import com.oksocios.model.User;
import com.oksocios.service.EstablishmentService;
import com.oksocios.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class EstablishmentController {

    private final UserService userService;
    private final EstablishmentService establishmentService;

    @Autowired
    public EstablishmentController(UserService userService, EstablishmentService establishmentService) {
        this.userService = userService;
        this.establishmentService = establishmentService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/establishments")
    public String selectEstablishments(Principal principal, Model model){
        User user =(User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        List<Establishment> establishments = establishmentService.getEstablishmentsByUserId(user.getId());
        if(establishments.size() == 1){
            return "redirect:/get-entries?establishment=" + establishments.get(0).getId();
        }
        model.addAttribute("user", user);
        model.addAttribute("hasEstablishments", establishments.size()>0);
        model.addAttribute("establishments", establishments);
        return "establishments";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/establishments/ajax")
    public ResponseEntity<List<Establishment>> getUserEstablishments(Principal principal){
        User user =(User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        List<Establishment> establishments = establishmentService.getEstablishmentsByUserId(user.getId());
        return new ResponseEntity<>(establishments, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/new-establishment")
    public String createEstablishmentsView(Principal principal, Model model){
        User user = userService.getUserByEmail(principal.getName());
        Establishment establishment = new Establishment();
        establishment.setUser(user);
        model.addAttribute("establishment", establishment);
        model.addAttribute("user", user);
        return "new-establishment";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/establishments")
    private String createEstablishment(@ModelAttribute Establishment establishment, HttpSession session){
        Establishment establishmentResponse = establishmentService.addEstablishment(establishment);
        session.setAttribute("idEstablishment", establishmentResponse.getId());
        return "redirect:/home";
    }
}
