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
@Table(name = "horaires")
@Setter @Getter @ToString @NoArgsConstructor
public class Horaire extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "horaire_dentiste",
            joinColumns = { @JoinColumn(name = "horaire_id") },
            inverseJoinColumns = { @JoinColumn(name = "dentiste_id") })
    private Set<Dentiste> dentistes =new HashSet<>();

    @ManyToOne()
    @JoinColumn(name="jour_id",nullable = false)
    private Jour jour;

}
