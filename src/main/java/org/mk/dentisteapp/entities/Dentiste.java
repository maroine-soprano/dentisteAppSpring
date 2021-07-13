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
@Table(name = "dentistes")
@Setter @Getter @ToString @NoArgsConstructor
public class Dentiste extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private int teleAccueil;
    private int teleUrgence;
    private String specialite;
    private int numero;
    private String position;
    private String photo;
    private String carte;

    @JsonIgnore
    @OneToOne(mappedBy = "dentiste",cascade = CascadeType.ALL)
    private User user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
    },
            mappedBy = "dentistes")
    private Set<Horaire> horaires=new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "dentiste",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Sujet> sujets=new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "dentiste",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Commentaire> commentaires=new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "dentiste",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Salon> salons=new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    },
            mappedBy = "dentistes")
    private Set<Private> salonsPrivate=new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "dentiste",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Demande> demandes=new HashSet<>();

    public Dentiste(Long id, String nom, String prenom, String adresse, int teleAccueil, int teleUrgence, String specialite, int numero, String position, String photo, String carte) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.teleAccueil = teleAccueil;
        this.teleUrgence = teleUrgence;
        this.specialite = specialite;
        this.numero = numero;
        this.position = position;
        this.photo = photo;
        this.carte = carte;
    }
}
