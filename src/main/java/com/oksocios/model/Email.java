package com.oksocios.model;

import java.util.Map;

public class Email {

    private String nameFrom;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String message;
    private String contentType;
    private Map< String, Object > model;

    public Email() {
        contentType = "text/plain";
    }

    public Email(String emailTo, String subject, String message) {
        this.emailTo = emailTo;
        this.subject = subject;
        this.message = message;
        this.contentType = "text/plain";
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }

    public String getNameFrom() {
        return nameFrom;
    }

    public void setNameFrom(String nameFrom) {
        this.nameFrom = nameFrom;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }
}
