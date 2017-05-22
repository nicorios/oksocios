package com.oksocios.model;

import javax.persistence.*;

/**
 * Created by Envy on 21/5/2017.
 */
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
    private String subscriptionDate;
    @Column(name = "expiration_date")
    private String expirationDate;
    @Column(name = "price")
    private Double price;

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

    public String getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(String subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
