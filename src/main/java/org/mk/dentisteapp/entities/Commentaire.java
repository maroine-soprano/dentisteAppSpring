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
@Table(name = "commentaires")
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn( name = "type" )
@Setter @Getter @ToString @NoArgsConstructor
public class Commentaire extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @Transient
    private String author;
    @Transient
    private Long authorId;
    @Transient
    private String type;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name="dentiste_id")
    private Dentiste dentiste;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name="sujet_id")
    private Sujet sujet;

    @OneToMany(mappedBy = "commentaire",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<FichierCmnt> fichiersCmnt=new HashSet<>();

    public Commentaire(String text) {
        this.text = text;
    }

    @PostLoad
    public void setAuthor(){
        this.author=this.dentiste.getPrenom()+" "+this.getDentiste().getNom();
        this.authorId=this.dentiste.getId();
    }

}
