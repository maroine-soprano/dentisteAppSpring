package org.mk.dentisteapp.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue( value="private" )
public class Private extends Salon{
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "private_dentiste",
            joinColumns = { @JoinColumn(name = "private_id") },
            inverseJoinColumns = { @JoinColumn(name = "dentiste_id") })
    private Set<Dentiste> dentistes =new HashSet<>();
}
