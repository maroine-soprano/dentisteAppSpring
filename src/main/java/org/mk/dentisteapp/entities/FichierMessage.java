package org.mk.dentisteapp.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mk.dentisteapp.entities.Auditing.Auditable;

import javax.persistence.*;

@Entity
@Table(name = "fichiersMessage") @Setter @Getter @ToString @NoArgsConstructor
public class FichierMessage extends Auditable<String> {
    @Id
    @Column(name = "chemin", nullable = false,unique = true)
    private String chemin;
    private String type;

    @ManyToOne()
    @JoinColumn(name="message_id")
    private Message message;

    public FichierMessage(String chemin, String type) {
        this.chemin = chemin;
        this.type = type;
    }
}
