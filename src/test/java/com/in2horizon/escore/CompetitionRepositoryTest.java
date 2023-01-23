package com.in2horizon.escore;

import com.in2horizon.escore.model.*;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;

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
        comp2 = new Competition( "comp2", user1, Set.of(user1, user2));

        comp1=compRepo.save(comp1);
        comp2=compRepo.save(comp2);

    }

    @Test
    public void findWhereContainsUserByIdShouldReturnCompetition() {
        List<Competition> result = compRepo.findWhereContainsUser(user3);
   //     log.info("zawartość result: "+ result.get(0).toString()+" :: "+comp1.toString());
        Assertions.assertTrue(result.contains(comp1));

        result = compRepo.findWhereContainsUser(user2);
        Assertions.assertFalse(result.contains(comp1));

    }
}
