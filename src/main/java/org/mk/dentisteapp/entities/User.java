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
@Table(name = "users", schema = "public")
@Setter @Getter @ToString @NoArgsConstructor
public class User extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "email", length = 64)
    private String email;
    @Column(name = "password", length = 64)
    private String password;
    private boolean active;

    private boolean confirme;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dentiste_id")
    private Dentiste dentiste;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Notification> notifications=new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    },
            mappedBy = "users")
    private Set<Report> reports=new HashSet<>();

    public User(Long id, String email, String password, boolean active, boolean confirme) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.active = active;
        this.confirme = confirme;
    }
}
