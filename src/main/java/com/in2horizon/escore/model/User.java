package com.in2horizon.escore.model;


import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@ToString
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)

@Setter
@Getter
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "password", "email"})})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String email;

@NonNull
//    @ElementCollection

@ManyToMany(/*mappedBy = "authority"*/)
    private Set<Authority> authorities;


   /* @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private Set<RoleAssoc> roleAssocs = new HashSet<RoleAssoc>();
*/

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



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
 //        List<Authority> result = roleAssocs.stream().map(it -> it.authority).distinct().toList();
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
