package com.oksocios.controller;

import com.oksocios.model.Email;
import com.oksocios.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/contact-us")
    public ResponseEntity<?> sendEmail(@RequestBody Email email) throws MessagingException {
        emailService.mailContactUs(email);
        return new ResponseEntity<Object>(true, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customers/{id}/email")
    public ResponseEntity<?> sendEmailToCustomer(@PathVariable Long id, @RequestBody Email email, @SessionAttribute Long idEstablishment) throws MessagingException {
        emailService.mailToCustomer(email, id, idEstablishment);
        return new ResponseEntity<Object>(true, HttpStatus.OK);
    }
}
