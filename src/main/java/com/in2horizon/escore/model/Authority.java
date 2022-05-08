package com.in2horizon.escore.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class Authority implements GrantedAuthority {
    @Id
  //  @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;
    private final String authority;


}