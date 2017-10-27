package com.oksocios.service;


import com.oksocios.model.Email;
import com.oksocios.model.Establishment;
import com.oksocios.model.User;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final Configuration fmConfiguration;
    private final EstablishmentService establishmentService;
    private final UserService userService;

    @Value("${spring.mail.username}")
    private String oksEmail;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, Configuration fmConfiguration, EstablishmentService establishmentService, UserService userService) {
        this.javaMailSender = javaMailSender;
        this.fmConfiguration = fmConfiguration;
        this.establishmentService = establishmentService;
        this.userService = userService;
    }

    @Async
    public void sendMail(Email email, String template) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setSubject(email.getSubject());
        mimeMessageHelper.setTo(email.getEmailTo());
        email.setMessage(getContentFromTemplate(email.getModel(), template));
        mimeMessageHelper.setText(email.getMessage(), true);
        javaMailSender.send(mimeMessageHelper.getMimeMessage());
    }

    private String getContentFromTemplate(Map< String, Object > model, String template) {
        StringBuffer content = new StringBuffer();
        try {
            content.append(FreeMarkerTemplateUtils
                    .processTemplateIntoString(fmConfiguration.getTemplate(template), model));
        } catch (Exception e) {

        }
        return content.toString();
    }


    public void mailContactUs(Email email) throws MessagingException {
        Map<String,Object> model = new LinkedHashMap<>();
        email.setEmailTo(oksEmail);
        email.setSubject("Mensaje de posible cliente!!");
        model.put("name",email.getNameFrom());
        model.put("email",email.getEmailFrom());
        model.put("message",email.getMessage());
        email.setModel(model);
        sendMail(email,"email/contact-us.ftl");
    }

    public void mailToCustomer(Email email,Long userId, Long idEstablishment) throws MessagingException {
        Establishment establishment = establishmentService.getEstablishmentById(idEstablishment);
        User customer = userService.getUser(userId);
        Map<String,Object> model = new LinkedHashMap<>();
        email.setEmailTo(customer.getEmail());
        email.setSubject("Nuevo Mensaje de " + establishment.getName());
        model.put("name", customer.getName());
        model.put("establishment", establishment.getName());
        model.put("message",email.getMessage());
        email.setModel(model);
        sendMail(email,"email/simple-mail.ftl");
    }
}
