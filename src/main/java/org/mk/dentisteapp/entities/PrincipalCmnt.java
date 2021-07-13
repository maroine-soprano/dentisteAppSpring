package org.mk.dentisteapp.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue( value="principal" )
@Setter
@Getter
@ToString
@NoArgsConstructor
public class PrincipalCmnt extends Commentaire {

    @OneToMany(mappedBy = "principalCmnt",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<SousCmnt> sousCmnts=new HashSet<>();

}
