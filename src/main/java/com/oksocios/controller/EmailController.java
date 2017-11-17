package com.oksocios.controller;

import com.oksocios.model.Email;
import com.oksocios.model.Establishment;
import com.oksocios.model.User;
import com.oksocios.service.EmailService;
import com.oksocios.service.EstablishmentService;
import com.oksocios.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
public class EmailController {

    private final EmailService emailService;
    private final EstablishmentService establishmentService;
    private final UserService userService;

    @Autowired
    public EmailController(EmailService emailService, EstablishmentService establishmentService, UserService userService) {
        this.emailService = emailService;
        this.establishmentService = establishmentService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/contact-us")
    public ResponseEntity<?> sendEmail(@RequestBody Email email) throws MessagingException {
        emailService.mailContactUs(email);
        return new ResponseEntity<Object>(true, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customers/{id}/email")
    public ResponseEntity<?> sendEmailToCustomer(@PathVariable Long id, @RequestBody Email email, @SessionAttribute Long idEstablishment) throws MessagingException {
        Establishment establishment = establishmentService.getEstablishmentById(idEstablishment);
        User customer = userService.getUser(id);
        emailService.mailToCustomer(email, customer, establishment);
        return new ResponseEntity<Object>(true, HttpStatus.OK);
    }
}
