package com.oksocios.controller;

import com.oksocios.exceptions.ObjectAlreadyExistsException;
import com.oksocios.exceptions.ObjectNotAccesibleException;
import com.oksocios.model.User;
import com.oksocios.model.UserRole;
import com.oksocios.model.UserRoleId;
import com.oksocios.service.UserService;
import com.oksocios.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.BadAttributeValueExpException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class StaffController {

    private final UserService userService;

    @Autowired
    public StaffController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/staff")
    private String getStaffView(Model model, @SessionAttribute Long idEstablishment){
        List<UserRole> staff = userService.getStaff(idEstablishment);
        List<UserRole> admins = new ArrayList<>();
        List<UserRole> employees = new ArrayList<>();
        staff.forEach(s -> {
            if(s.getRol().equals(Constants.ROLE_NAME_ADMIN)){
                admins.add(s);
            }else{
                employees.add(s);
            }
        });
        model.addAttribute("employees", employees);
        model.addAttribute("admins", admins);
        model.addAttribute("user", new User());
        return "staff";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/staff/{id}")
    public ResponseEntity<UserRole> getStaff(@PathVariable Long id, @SessionAttribute Long idEstablishment){
        return new ResponseEntity<>(userService.getStaffById(id, idEstablishment), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/staff")
    public ResponseEntity<Object> addCustomer(@RequestBody User user, @SessionAttribute Long idEstablishment) throws ObjectNotAccesibleException {
        HashMap<String, Object> response = new HashMap<>();
        if ((user.getDni() == null) || (user.getEmail().isEmpty())) {
            return new ResponseEntity<>("Por favor, Ingrese el DNI e Email del nuevo socio", HttpStatus.OK);
        }
        try {
            if(user.getId()!=null){
                UserRole userRole = userService.updateUser(user, idEstablishment);
                response.put("userRole", userRole);
                response.put("update", true);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            userService.addUser(user, user.getRole(), idEstablishment);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
        UserRole userRole = new UserRole(new UserRoleId(user, user.getRole(), idEstablishment),
                Constants.getRoleName(user.getRole()));
        response.put("userRole", userRole);
        response.put("update", false);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/staff/{id}")
    public ResponseEntity<Boolean> deleteActivity(@PathVariable Long id, @SessionAttribute Long idEstablishment) throws BadAttributeValueExpException {
        userService.deleteUser(id, idEstablishment);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
