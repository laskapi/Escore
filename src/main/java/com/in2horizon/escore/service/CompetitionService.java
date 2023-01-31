package com.in2horizon.escore.service;

import com.in2horizon.escore.model.Competition;
import com.in2horizon.escore.model.CompetitionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class CompetitionService {
    @Autowired
    CompetitionRepository compRepo;

    final String TAG = "CompetitionSerice: ";

    public List<Competition> getCompetitions() {
        List<Competition> comps = compRepo.findAll();
        return comps;
    }

    public ResponseEntity<Competition> getCompetition(Long id) {
        Optional<Competition> comp = compRepo.findById(id);

        try {
            return ResponseEntity.ok(comp.orElseThrow());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Competition> addCompetition(Competition comp) {
        Competition existing = compRepo.findById(comp.getId()).orElse(null);
        if (existing == null) {
            if (comp.getName().isEmpty() || comp.getAdmin()==null)
            {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }

            Competition saved=compRepo.save(comp);
            log.info(saved.toString());
            return ResponseEntity.ok(saved);
        }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    @Transactional
    public ResponseEntity<Competition> updateCompetition(Competition comp) {
        Competition existing = compRepo.findById(comp.getId()).orElse(null);
        if (existing != null) {
            existing.setName(comp.getName());
            existing.setAdmin(comp.getAdmin());
            existing.setUsers(comp.getUsers());
            Competition saved=compRepo.save(existing);
            log.info("saved Users for competition: "+ saved.getUsers());
            return ResponseEntity.ok(saved);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);


    }

    public ResponseEntity<Void> deleteCompetition(Long id) {
        try {
            compRepo.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }

    }

}
