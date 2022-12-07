package com.in2horizon.escore;


import com.in2horizon.escore.model.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
@WebAppConfiguration

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DbSchemaTests {
    @Autowired
    DataService dataService;

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleAssocRepository roleAssocRepo;


    @Autowired
    PasswordEncoder encoder;



    final Logger logger =
            LoggerFactory.getLogger(DbSchemaTests.class);


    @BeforeEach
    public void Init() {
        User user = new User(1L, "john", encoder.encode("haslo"), "john@duzy.com");
        user = userRepo.save(user); //dataService.saveUser(user);


    }


    @Test
    public void userRepo_userAdded() {

        User savedUser = /*userRepo.findByUsername("john");*/ dataService.getUserByName("john");
        logger.info("MY USER AUTHORITIES ARE:"+savedUser.getAuthorities());
        assertEquals("john", savedUser.getUsername());
    }

    @Test
    public void roleAssoc_userAdded() {
        long amount = roleAssocRepo.count();
        assertTrue(amount > 0);
        Iterable<RoleAssoc> roles = roleAssocRepo.findAll();
        log.info("MY ROLES:  "+roles.toString());

    }


}
