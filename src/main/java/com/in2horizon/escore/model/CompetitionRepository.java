package com.in2horizon.escore.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompetitionRepository extends CrudRepository<Competition,Long> {

  //  List<Competition> findByAdminUsername(String username);
    //List<Competition> findByAdminId(Long id);

    @Query("SELECT c from Competition c WHERE ?1 MEMBER of c.users OR ?1 = c.admin")
    List<Competition> findWhereContainsUser(User user);
}

