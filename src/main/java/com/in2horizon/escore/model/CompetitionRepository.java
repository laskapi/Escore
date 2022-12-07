package com.in2horizon.escore.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompetitionRepository extends CrudRepository<Competition,Long> {

  //  List<Competition> findByAdminUsername(String username);
    //List<Competition> findByAdminId(Long id);

}

