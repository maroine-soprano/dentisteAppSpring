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
@Table(name = "messages")
@Setter @Getter @ToString @NoArgsConstructor
public class Message extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String txt;
    @Transient
    private String author;
    @Transient
    private Long authorId;
    @Transient
    private Long salonId;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name="dentiste_id")
    private Dentiste dentiste;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name="salon_id")
    private Salon salon;

    @OneToMany(mappedBy = "message",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<FichierMessage> fichiersMessage=new HashSet<>();

    public Message(String txt) {
        this.txt = txt;
    }

    @PostLoad
    public void setAuthor(){
        this.author=this.dentiste.getPrenom()+" "+this.getDentiste().getNom();
        this.authorId=this.dentiste.getId();
    }
}
