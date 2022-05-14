package com.in2horizon.escore.model;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"username","password"})})
@Data
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)

public class User implements UserDetails{

    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 //   @Column(unique = true)
    private final String username;
    private final String password;


  @ManyToOne(fetch = FetchType.EAGER,optional = false)
   @JoinColumn
    private final Authority authority;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(
                authority
                /*new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_SUPER";
            }
        }*/
        );
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
