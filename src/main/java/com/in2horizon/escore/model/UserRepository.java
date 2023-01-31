package com.in2horizon.escore.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {

List<User> findByUsername(String username);
//List<User> findByUsernameAndPassword(String username, String password);
Optional<User> findByEmail(String email);
@Override
List<User> findAll();
}

