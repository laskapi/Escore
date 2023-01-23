package com.in2horizon.escore.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Setter
@Getter

@Table(name = "competitions")
public class Competition {
    public Competition(String name) {
        this.name = name;
    }

    public static final String HIDDEN = "";



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(unique = true)
    private String name;


    /*@Column(columnDefinition = "boolean default false")
    private final Boolean archived = false;
*/
    @NonNull
    @ManyToOne()//(fetch = FetchType.EAGER, optional = false)
    private User admin;

    @NonNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_competitions",
            joinColumns = {
                    @JoinColumn(name = "competition_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id",
                            nullable = false, updatable = false)}
    )
    private Set<User> users;// = new HashSet<>();


//        @ManyToMany(mappedBy = "competitions", fetch = FetchType.LAZY)
    //       private Set<User> users = new HashSet<>();

    /*
        @OneToMany(mappedBy = "competition",cascade = CascadeType.PERSIST)
        Set<CUA> competitionUserAuthorities;
    */

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Competition that = (Competition) o;

        return name.equals(that.name);
    }
}
