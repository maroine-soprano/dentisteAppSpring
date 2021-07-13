package org.mk.dentisteapp.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mk.dentisteapp.entities.Auditing.Auditable;

import javax.persistence.*;

@Entity
@Table(name = "demandes")
@Setter @Getter @ToString @NoArgsConstructor
public class Demande extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean accepted;

    @ManyToOne()
    @JoinColumn(name="dentiste_id")
    private Dentiste dentiste;

    @ManyToOne()
    @JoinColumn(name="publicSalon_id")
    private Public publicSalon;
}
