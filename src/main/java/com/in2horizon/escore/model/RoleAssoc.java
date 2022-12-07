package com.in2horizon.escore.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import javax.persistence.*;

import java.io.Serializable;
@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class RoleAssoc {

    @EmbeddedId
    RoleAssocKey id=new RoleAssocKey();

    @ManyToOne(cascade=CascadeType.MERGE)
    @MapsId("competitionId")
    Competition competition;

    @ManyToOne(cascade = CascadeType.MERGE)
    @MapsId("userId")
    User user;

    @ManyToOne
    @MapsId("authorityId")
    Authority authority;

    public RoleAssoc(Competition competition, User user, Authority authority) {
        this.competition = competition;
        this.user = user;
        this.authority = authority;
    }

}

@Embeddable
class RoleAssocKey implements Serializable {

    @Column(name="competition_id")
    Long competitionId;

    @Column (name="user_id")
    Long userId;

    @Column (name="authority_id")
    Long authorityId;
}