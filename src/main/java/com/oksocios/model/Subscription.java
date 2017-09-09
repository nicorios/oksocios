package com.oksocios.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_subscription")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_establishment")
    private Establishment establishment;
    @ManyToOne
    @JoinColumn(name = "id_activity")
    private Activity activity;
    @Column(name = "subscription_date")
    private Date subscriptionDate;
    @Column(name = "expiration_date")
    private Date expirationDate;
    @Column(name = "price")
    private Double price;
    @Column(name = "classes_left")
    private Integer classesLeft;
    @Column(name = "freepass")
    private Boolean freePass;

    @Transient
    private Integer period;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getClassesLeft() {
        return classesLeft;
    }

    public void setClassesLeft(Integer classesLeft) {
        this.classesLeft = classesLeft;
    }

    public Boolean getFreePass() {
        return freePass;
    }

    public void setFreePass(Boolean freepass) {
        this.freePass = freepass;
    }
}
