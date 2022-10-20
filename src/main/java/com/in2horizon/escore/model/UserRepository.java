package com.in2horizon.escore.model;

import com.in2horizon.escore.model.User;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {

List<User> findByUsername(String username);
List<User> findByUsernameAndPassword(String username, String password);
}

