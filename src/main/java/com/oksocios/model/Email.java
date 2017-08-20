package com.oksocios.model;

public class Email {

    private User to;
    private String emailTo;
    private String subject;
    private String message;

    public Email(){}

    public Email(User to, String emailTo, String subject, String message) {
        this.to = to;
        this.emailTo = emailTo;
        this.subject = subject;
        this.message = message;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
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
}
