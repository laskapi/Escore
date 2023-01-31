package com.in2horizon.escore;

import com.in2horizon.escore.model.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class CompetitionRepositoryTest {

    @Autowired
    private CompetitionRepository compRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AuthorityRepository authRepo;

    @Autowired
    PasswordEncoder encoder;

    private User user1, user2, user3;
    private Competition comp1, comp2;
    private Authority auth;


    @BeforeEach
    public void setUp() {

        auth = new Authority("ADMIN");
        auth=authRepo.save(auth);


        user1 = new User("one", encoder.encode("one"),
                "one@in2horizon.com", Set.of(auth));
        user2 = new User("two", encoder.encode("two"),
                "two@in2horizon.com", Set.of(auth));
        user3 = new User("three", encoder.encode("three"),
                "three@in2horizon.com", Set.of(auth));

        user1=userRepo.save(user1);
        user2=userRepo.save(user2);
        user3=userRepo.save(user3);

        comp1 = new Competition("comp1", user1, Set.of( user3));
        comp2 = new Competition( "comp2", user3, Set.of(user1, user2));

        comp1=compRepo.save(comp1);
        comp2=compRepo.save(comp2);

    }

    @AfterEach
    public void tearDown(){
        compRepo.deleteAll();
        userRepo.deleteAll();
        authRepo.deleteAll();
    }

    @Test
    public void findByUser_whenContainsAsAdmin_returnsCompetition() {
        List<Competition> result = compRepo.findByUser(user3);
        assertTrue(result.contains(comp2));
    }
    @Test
    public void findByUser_whenContainsInUsers_returnsCompetition() {
        List<Competition> result = compRepo.findByUser(user2);
        assertTrue(result.contains(comp2));

    }
}
