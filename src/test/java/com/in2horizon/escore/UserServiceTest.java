package com.in2horizon.escore;


import com.in2horizon.escore.model.Authority;
import com.in2horizon.escore.model.AuthorityRepository;
import com.in2horizon.escore.model.User;
import com.in2horizon.escore.model.UserRepository;
import com.in2horizon.escore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
@WebAppConfiguration

public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    AuthorityRepository authRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    PasswordEncoder encoder;


    private final static String email = "john@in2horizon.com";
    private final static String newName = "newName";

    private User user;
    private Authority auth = new Authority("ADMIN");

    @BeforeEach
    void setUp() {
        auth = authRepo.save(auth);

        user = new User("john", encoder.encode("one"),
                email, Set.of(auth));

        user = userRepo.save(user);



    }

    @AfterEach
    void tearDown() {
        userRepo.deleteAll();
        user = null;
        authRepo.deleteAll();
    }


    @Test
    public void addUser_persistsUser() {
        userRepo.deleteAll();
        user=userService.addUser(user).getBody();

        User persisted=userService.getUser(user.getId()).getBody();
        assertEquals(user.getId(),persisted.getId());

    }

    @Test
    public void addUser_duplicateId_returnsConflict() {

        user.setUsername("change");
        ResponseEntity response = userService.addUser(user);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void addUser_duplicateEmail_returnsConflict() {

        user = new User("Joe", "pass", email, Set.of(auth));
        ResponseEntity response = userService.addUser(user);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

    }

    @Test
    public void updateUser_changeName_persistsNewName() {

        user.setUsername(newName);
        userService.updateUser(user);
        User persisted = userService.getUser(user.getId()).getBody();
        assertEquals(newName, persisted.getUsername());
    }

    @Test
    public void deleteUser_givenId_removesUser(){

        userService.deleteUser(user.getId());
        assertEquals(HttpStatus.NOT_FOUND,userService.getUser(user.getId()).getStatusCode());
    }


}
