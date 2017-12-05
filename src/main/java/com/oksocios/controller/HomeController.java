package com.oksocios.controller;

import com.oksocios.model.User;
import com.oksocios.service.EntryService;
import com.oksocios.service.UserService;
import com.oksocios.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("idEstablishment")
public class HomeController {

    private final EntryService entryService;
    private final UserService userService;
    @Value("${oksocios.demo.userId}")
    private Long demoId;
    @Value("${oksocios.demo.establishmentId}")
    private Long demoEstablishmentId;

    @Autowired
    public HomeController(EntryService entryService, UserService userService){
        this.entryService = entryService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String getLanding(){
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String loginUser() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login-error")
    public String loginError(Model model) {
        model.addAttribute("error", true);
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/recovery-password")
    public String recoveryPasswordView(){
        return "user-forgot-password";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/recovery-password")
    public ResponseEntity<Boolean> recoveryPassword(@RequestBody String email) throws MessagingException {
        User user = userService.recoveryPassword(email);
        return new ResponseEntity<>(user != null, HttpStatus.OK);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get-entries")
    public String setSessionIdEstablishment(Model model, @RequestParam(name = "establishment") Long idEstablishment, Principal principal){
        User user =(User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        userService.updateRole(user.getId(), idEstablishment);
        model.addAttribute("idEstablishment", idEstablishment);
        return "redirect:/home";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/home")
    public String getCurrentUsers(Model model, @SessionAttribute Long idEstablishment, @SessionAttribute Boolean isDemo){
        model.addAttribute("entries", entryService.getAllEntriesByEstablishmentIdInLastTwoHours(idEstablishment));
        model.addAttribute("isDemo", isDemo);
        return "home";
    }

    @RequestMapping(method = RequestMethod.GET, value = "new-entry")
    public String getNewEntryView(){
        return "new-entry";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/demo")
    private String checkDemo(HttpServletRequest request){
        User demo = userService.getUser(demoId);
        Authentication auth =
                new UsernamePasswordAuthenticationToken(demo, null, demo.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        userService.updateRole(demo.getId(), demoEstablishmentId);
        request.getSession().setAttribute("idEstablishment", demoEstablishmentId);
        request.getSession().setAttribute("isDemo", true);
        return "redirect:/home";
    }
}
