package com.oksocios.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserRoleId implements Serializable{

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @Column(name = "id_rol")
    private Integer roleId;
    @Column(name = "id_establishment")
    private Long establishmentId;

    public UserRoleId() {
    }

    public UserRoleId(User user, Integer roleId, Long establishmentId) {
        this.user = user;
        this.roleId = roleId;
        this.establishmentId = establishmentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Long getEstablishmentId() {
        return establishmentId;
    }

    public void setEstablishmentId(Long establishmentId) {
        this.establishmentId = establishmentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user.getId(), roleId, establishmentId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRoleId)) return false;
        UserRoleId that = (UserRoleId) o;
        return Objects.equals(user.getId(), that.getUser().getId()) &&
                Objects.equals(roleId, that.getRoleId()) &&
                Objects.equals(establishmentId, that.getEstablishmentId());
    }
}
