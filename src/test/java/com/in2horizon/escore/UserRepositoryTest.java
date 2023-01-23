package com.in2horizon.escore;


import com.in2horizon.escore.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
@WebAppConfiguration

public class UserRepositoryTest {
    /*@Autowired
    DataService dataService;
*/
    @Autowired
    UserRepository userRepo;

    @Autowired
    AuthorityRepository authRepo;

    @Autowired
    PasswordEncoder encoder;


    private Authority auth;
    private User user;


    @BeforeEach
    public void Init() {

        auth = new Authority("ADMIN");
        auth = authRepo.save(auth);

        user = new User("john", encoder.encode("one"),
                "john@in2horizon.com", Set.of(auth));
        user = userRepo.save(user);


    }


    @Test
    public void userRepo_userAdded() {

        List<User> users = userRepo.findByUsername("john");
        assertEquals("john", users.get(0).getUsername());
    }
}
