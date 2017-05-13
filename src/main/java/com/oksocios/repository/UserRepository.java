package com.oksocios.repository;

import com.oksocios.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Envy on 11/5/2017.
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
