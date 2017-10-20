package com.oksocios.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserRoleId implements Serializable{

    @Column(name = "id_user")
    private Long userId;
    @Column(name = "id_rol")
    private Integer roleId;
    @Column(name = "id_establishment")
    private Long establishmentId;

    public UserRoleId() {
    }

    public UserRoleId(Long userId, Integer roleId, Long establishmentId) {
        this.userId = userId;
        this.roleId = roleId;
        this.establishmentId = establishmentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        return Objects.hash(userId, roleId, establishmentId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRoleId)) return false;
        UserRoleId that = (UserRoleId) o;
        return Objects.equals(userId, that.getUserId()) &&
                Objects.equals(roleId, that.getRoleId()) &&
                Objects.equals(establishmentId, that.getEstablishmentId());
    }
}
