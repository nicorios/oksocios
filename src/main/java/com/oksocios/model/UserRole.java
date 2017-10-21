package com.oksocios.model;

import javax.persistence.*;

@Entity
@Table(name = "user_rol")
public class UserRole {

    @EmbeddedId
    private UserRoleId id;
    @Column(name = "rol")
    private String rol;

    public UserRole() {
    }

    public UserRole(UserRoleId id, String rol) {
        this.id = id;
        this.rol = rol;
    }

    public UserRoleId getId() {
        return id;
    }

    public void setId(UserRoleId id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}