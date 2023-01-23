package com.in2horizon.escore.service;

import com.in2horizon.escore.model.Competition;
import com.in2horizon.escore.model.CompetitionRepository;
import com.in2horizon.escore.model.RoleAssoc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class CompetitionService {
    @Autowired
    CompetitionRepository compRepo;

    final String TAG = "CompetitionSerice: ";

    public Iterable<Competition> getCompetitions() {
        log.info(TAG + "comps::entering ");
        Iterable<Competition> comps = compRepo.findAll();
        comps = excludeHiddenCompetition(comps);
        log.info(TAG + "comps:: " + comps);
        return comps;
    }

    public ResponseEntity<Competition> getCompetition(Long id) {
        Optional<Competition> comp = compRepo.findById(id);
        if (comp.get().getName().equals(Competition.HIDDEN)) {
            throw new NoSuchElementException();
        }
        try {
            return ResponseEntity.ok(comp.orElseThrow());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> addCompetition(Competition comp) {
        Competition existing = compRepo.findById(comp.getId()).orElse(null);
        if (existing == null) {
            /*if (comp.getName().isEmpty())

                    || comp.getPassword().isEmpty()
                    || comp.getEmail().isEmpty())
            {
                return new ResponseEntity<String>("Please fill all required fields", HttpStatus.BAD_REQUEST);
            }
            */
            log.info(compRepo.save(comp).toString());
            return ResponseEntity.ok("Competition added");
        }
        return new ResponseEntity<String>("Competition already exists", HttpStatus.CONFLICT);
    }

    @Transactional
    public ResponseEntity<String> updateCompetition(Competition comp) {
        Competition existing = compRepo.findById(comp.getId()).orElse(null);
        if (existing != null) {
            existing.setName(comp.getName());
            existing.setAdmin(comp.getAdmin());
            existing.setUsers(comp.getUsers());
            log.info("saved Users for competition: "+compRepo.save(existing).getUsers().toString());
            return ResponseEntity.ok("user updated");
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);


    }

    public ResponseEntity<Void> deleteCompetition(Long id) {
        try {
          /*  Competition comp = (compRepo.findById(id)).get();
            List<RoleAssoc> assocs = roleAssocRepo.findAllByCompetition(comp);
            assocs.forEach(roleAssocRepo::delete);

            compRepo.delete(comp);
          */

            compRepo.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }

    }



    Iterable<Competition> excludeHiddenCompetition(Iterable<Competition> comps) {
        return StreamSupport.stream(comps.spliterator(), false).filter(c -> !c.getName().equals(Competition.HIDDEN)).collect(Collectors.toList());

    }
}
