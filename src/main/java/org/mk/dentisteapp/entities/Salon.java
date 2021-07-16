package org.mk.dentisteapp.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mk.dentisteapp.entities.Auditing.Auditable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "salons")
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn( name = "type" ) @Setter @Getter @ToString @NoArgsConstructor
public class Salon extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private int nbrTotal;
    private boolean active;
    private String description;
    @Transient
    private String type;

    @ManyToOne()
    @JoinColumn(name="dentiste_id")
    private Dentiste dentiste;

    @OneToMany(mappedBy = "salon",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @OrderBy("createdDate ASC")
    private Set<Message> messages=new HashSet<>();

    public Salon(Long id, String nom, int nbrTotal, boolean active) {
        this.id = id;
        this.nom = nom;
        this.nbrTotal = nbrTotal;
        this.active = active;
    }

    @PostLoad
    public void setType(){
        if (this instanceof Public)this.type="public";
        else this.type="private";
    }
}
