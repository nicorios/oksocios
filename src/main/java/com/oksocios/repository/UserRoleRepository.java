package com.oksocios.repository;

import com.oksocios.model.UserRole;
import com.oksocios.model.UserRoleId;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, UserRoleId> {
    UserRole findFirstByIdUserIdAndIdRoleIdAndIdEstablishmentId(Long userId, Integer roleId, Long establishmentId);
    UserRole findFirstByIdUserIdAndIdRoleId(Long userId, Integer roleId);
}
