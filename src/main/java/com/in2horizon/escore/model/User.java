package com.in2horizon.escore.model;


import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@ToString
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "password", "email"})})
public class User implements UserDetails {

    //@Autowired
    //EntityManager em;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    final private Long id;

    @Getter
    @NonNull
    private String username;

    @Getter
    @NonNull
    private String password;

    @NonNull
    private String email;

    @OneToMany(mappedBy = "user")
@ToString.Exclude
    private Set<RoleAssoc> roleAssocs = new HashSet<RoleAssoc>();


    /* @JoinColumn
     @ElementCollection(fetch = FetchType.EAGER)
     private List<Authority> authorities;
 */
   /* @ManyToMany(fetch=FetchType.EAGER)//(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinTable(name = "users_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")


    )
    private Set<Authority> authorities = new HashSet<>();
*/

    /*  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
      @JoinTable(name = "users_competitions",
              joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id",
                      nullable = false, updatable = false)},
              inverseJoinColumns = {
                      @JoinColumn(name = "competition_id", referencedColumnName = "id",
                              nullable = false, updatable = false)})
      private Set<Competition> competitions = new HashSet<>();
  */

    /*
    public User(String username, String password, String email*//*, Set<Authority> authorities*//*) {
        this.username = username;
        this.password = password;
        this.email = email;
       // this.authorities = authorities;
    }
*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//       List<RoleAssoc> roleAssocs = roleAssocRepo.findAllByUser(this);

        List<Authority> result = roleAssocs.stream().map(it -> it.authority).distinct().toList();
        return result;
/*
       Authority authority = new Authority("TEST");
        assert authority != null;
        return List.of(
                authority);
*/
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
