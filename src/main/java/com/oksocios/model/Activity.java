package com.oksocios.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "activity")
public class Activity {

    @Id
    @Column(name = "id_activity")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "id_establishment")
    @JsonBackReference
    private Establishment establishment;

    public Activity() {
    }

    public Activity(Long id){
        this.id = id;
    }

    public Activity(String name, Establishment establishment) {
        this.name = name;
        this.establishment = establishment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }
}
