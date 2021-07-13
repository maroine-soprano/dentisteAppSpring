package org.mk.dentisteapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mk.dentisteapp.entities.Auditing.Auditable;

import javax.persistence.*;

@Entity
@Table(name = "fichiersSujet") @Setter @Getter @ToString @NoArgsConstructor
public class FichierSujet extends Auditable<String> {
    @Id
    @Column(name = "chemin", nullable = false,unique = true)
    private String chemin;
    private String type;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name="sujet_id")
    private Sujet sujet;

    public FichierSujet(String chemin, String type) {
        this.chemin = chemin;
        this.type = type;
    }
}
