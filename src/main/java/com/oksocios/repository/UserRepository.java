package com.oksocios.repository;

import com.oksocios.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    User findFirstByDni(Long dni);
    User findByEmailOrDni(String email, Long dni);
}
