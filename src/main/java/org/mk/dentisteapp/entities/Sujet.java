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
@Table(name = "sujets") @Setter @Getter @ToString @NoArgsConstructor
public class Sujet extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private boolean active;
    private boolean archive;
    @Transient
    private int nbrCmnts;

    @ManyToOne()
    @JoinColumn(name="dentiste_id")
    private Dentiste dentiste;

    @ManyToOne()
    @JoinColumn(name="categorie_id")
    private Categorie categorie;

    @JsonIgnore
    @OneToMany(mappedBy = "sujet",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Commentaire> commentaires=new HashSet<>();

    @OneToMany(mappedBy = "sujet",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<FichierSujet> fichiersSujet=new HashSet<>();

    public Sujet(Long id, String titre, String description, boolean active, boolean archive) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.active = active;
        this.archive = archive;
    }

    @PostLoad
    public void setNbrCmnts(){
        this.nbrCmnts=this.commentaires.size();
    }
}
