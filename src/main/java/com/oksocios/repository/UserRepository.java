package com.oksocios.repository;

import com.oksocios.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    User findByDni(Long dni);
}
