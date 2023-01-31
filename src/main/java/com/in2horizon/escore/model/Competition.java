package com.in2horizon.escore.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Setter
@Getter

@Table(name = "competitions")
public class Competition {





    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(unique = true)
    private String name;


    @NonNull
    @ManyToOne()
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
    private Set<User> users;



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
