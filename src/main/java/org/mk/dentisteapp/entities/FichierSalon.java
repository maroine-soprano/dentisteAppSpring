package org.mk.dentisteapp.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mk.dentisteapp.entities.Auditing.Auditable;

import javax.persistence.*;

@Entity
@Table(name = "fichiersSalon") @Setter @Getter @ToString @NoArgsConstructor
public class FichierSalon extends Auditable<String> {
    @Id
    @Column(name = "chemin", nullable = false,unique = true)
    private String chemin;
    private String type;

    @ManyToOne()
    @JoinColumn(name="salon_id")
    private Salon salon;

    public FichierSalon(String chemin, String type) {
        this.chemin = chemin;
        this.type = type;
    }
}
