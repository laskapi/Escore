package com.in2horizon.escore.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleAssocRepository extends CrudRepository<RoleAssoc, RoleAssocKey> {

List<RoleAssoc> findAllByUser(User user);
}
