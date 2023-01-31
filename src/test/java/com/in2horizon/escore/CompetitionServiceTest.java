package com.in2horizon.escore;

import com.in2horizon.escore.model.*;
import com.in2horizon.escore.service.CompetitionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Slf4j
@SpringBootTest
//@ExtendWith(MockitoExtension.class)
public class CompetitionServiceTest {

    @Autowired
    CompetitionService compService;

    @Autowired
    CompetitionRepository compRepo;

    @Autowired
    AuthorityRepository authRepo;

    @Autowired
    UserRepository userRepo;

    private Competition competition;

    private User user;
    private Authority auth = new Authority("ADMIN");
    private Set<User> users;

    @BeforeEach
    public void setUp() {

        auth = authRepo.save(auth);

        user = new User("john", "one",
                "aaa@aaa.com", Set.of(auth));
        user = userRepo.save(user);

        users = new HashSet();
        users.add(user);
        competition = new Competition("comp1", user, users);
        competition = compRepo.save(competition);
    }

    @AfterEach
    public void tearDown() {
        compRepo.deleteAll();
        userRepo.deleteAll();
        authRepo.deleteAll();
    }


    @Test
    public void getCompetitions_returnsCompetitions() {
        List<Competition> competitions = compService.getCompetitions();
        assertFalse(competitions.isEmpty());
    }

    @Test
    public void getCompetition_whenExists_returnCompetition() {
        Competition comp = compService.getCompetition(competition.getId()).getBody();
        assertEquals(competition.getId(), comp.getId());
    }

    @Test
    public void addCompetition_persistsCompetition() {
        compRepo.deleteAll();
        competition = compService.addCompetition(competition).getBody();

        Competition persisted = compService.getCompetition(competition.getId()).getBody();
        assertEquals(competition.getId(), persisted.getId());
    }

    @Test
    public void updateCompetition_changeName_persistsNewName() {
        String newName = "newName";
        competition.setName(newName);
        compService.updateCompetition(competition).getBody();
        Competition persisted = compService.getCompetition(competition.getId()).getBody();
        assertEquals(newName, persisted.getName());

    }

    @Test
    public void deleteCompetition_givenId_removesCompetition() {
        compService.deleteCompetition(competition.getId());
        assertEquals(HttpStatus.NOT_FOUND,compService.getCompetition(competition.getId()).getStatusCode());

    }
}