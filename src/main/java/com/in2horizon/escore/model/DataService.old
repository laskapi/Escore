package com.in2horizon.escore.model;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class DataService {
@Autowired
    EntityManager em;
    @Autowired
    UserRepository userRepo;

    @Transactional
    public User getUserByName(String name) {
        User user= userRepo.findByUsername(name).get(0);
        Hibernate.initialize(user.getAuthorities());
        return user;
    }

    public User saveUser(User user) {
     return  userRepo.save(user);
    }
}
