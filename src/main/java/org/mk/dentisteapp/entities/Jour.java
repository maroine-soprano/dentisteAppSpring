package org.mk.dentisteapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mk.dentisteapp.entities.Auditing.Auditable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "jours")
@Setter @Getter @ToString @NoArgsConstructor
public class Jour extends Auditable<String> {
    @Id
    @Column(name = "nom", nullable = false,unique = true)
    private String nom;

    @JsonIgnore
    @OneToMany(mappedBy = "jour",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Horaire> horaires=new HashSet<>();

    public Jour(String nom) {
        this.nom = nom;
    }
}
