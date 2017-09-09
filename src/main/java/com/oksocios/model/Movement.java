package com.oksocios.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "balance")
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_balance")
    private Long id;
    @Column(name = "income")
    private Boolean income;
    @Column(name = "amount")
    private Double amount;
    @ManyToOne
    @JoinColumn(name="id_user")
    @JsonBackReference
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_concept")
    private Concept concept;
    @Column(name = "detail")
    private String detail;
    @ManyToOne
    @JoinColumn(name = "id_establishment")
    private Establishment establishment;
    @Column(name = "date")
    private Date date;

    public Movement() {
    }

    public Movement(Boolean income, Double amount, User user, Concept concept, String detail, Establishment establishment, Date date) {
        this.income = income;
        this.amount = amount;
        this.user = user;
        this.concept = concept;
        this.detail = detail;
        this.establishment = establishment;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIncome() {
        return income;
    }

    public void setIncome(Boolean income) {
        this.income = income;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Concept getConcept() {
        return concept;
    }

    public void setConcept(Concept concept) {
        this.concept = concept;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
