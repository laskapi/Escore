package com.in2horizon.escore.model;

import com.in2horizon.escore.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

User findByUsername(String username);
}

