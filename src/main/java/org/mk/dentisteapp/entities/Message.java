package org.mk.dentisteapp.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mk.dentisteapp.entities.Auditing.Auditable;

import javax.persistence.*;

@Entity
@Table(name = "messages")
@Setter @Getter @ToString @NoArgsConstructor
public class Message extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String txt;

    @ManyToOne()
    @JoinColumn(name="dentiste_id")
    private Dentiste dentiste;

    @ManyToOne()
    @JoinColumn(name="salon_id")
    private Salon salon;

    public Message(String txt) {
        this.txt = txt;
    }
}
