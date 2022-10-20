package com.in2horizon.escore.model;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "password"})})
@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //   @Column(unique = true)
    private final String username;
    private final String password;


//   @ManyToOne(fetch = FetchType.EAGER, optional = false)
   @JoinColumn
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Authority>  authorities;

    public User(String username, String password,List<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }


    @Override
    public Collection<? extends Authority> getAuthorities() {

/*
        assert authority != null;
        return List.of(
                authority);
*/
    return authorities;


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
