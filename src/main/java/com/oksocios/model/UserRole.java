package com.oksocios.model;

import javax.persistence.*;

@Entity
@Table(name = "user_rol")
public class UserRole {

    @EmbeddedId
    private UserRoleId id;
    @Column(name = "rol")
    private String rol;
    @Column(name = "id_establishment")
    private Long establishmentId;

    public UserRole() {
    }

    public UserRole(UserRoleId id, String rol, Long establishmentId) {
        this.id = id;
        this.rol = rol;
        this.establishmentId = establishmentId;
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

    public Long getEstablishmentId() {
        return establishmentId;
    }

    public void setEstablishmentId(Long establishmentId) {
        this.establishmentId = establishmentId;
    }
}
