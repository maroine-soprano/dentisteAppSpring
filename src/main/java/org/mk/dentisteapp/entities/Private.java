package org.mk.dentisteapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue( value="private" )
public class Private extends Salon{
    @JsonIgnore
    @OneToMany(mappedBy = "privateSalon",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Demande> demandes=new HashSet<>();
}
