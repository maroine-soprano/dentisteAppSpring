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
@Table(name = "categories")
@Setter @Getter @ToString @NoArgsConstructor
public class Categorie extends Auditable<String> {
    @Id
    @Column(name = "nom", nullable = false,unique = true)
    private String nom;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "categorie",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Sujet> sujets=new HashSet<>();
}
