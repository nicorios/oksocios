package com.oksocios.repository;

import com.oksocios.model.UserRole;
import com.oksocios.model.UserRoleId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRoleRepository extends CrudRepository<UserRole, UserRoleId> {
    UserRole findFirstByIdUserIdAndIdRoleIdAndIdEstablishmentId(Long userId, Integer roleId, Long establishmentId);
    UserRole findFirstByIdUserIdAndIdRoleId(Long userId, Integer roleId);
    List<UserRole> findAllByIdEstablishmentIdAndIdRoleIdIsIn(Long establishmentId, List<Integer> roles);
    UserRole findFirstByIdUserIdAndIdEstablishmentId(Long id, Long idEstablishment);
    List<UserRole> findAllByIdUserId(Long userId);
}
