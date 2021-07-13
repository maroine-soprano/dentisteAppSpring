package org.mk.dentisteapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue( value="sous" )
@Setter
@Getter
@ToString
@NoArgsConstructor
public class SousCmnt extends Commentaire{
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name="principalCmnt_id")
    private PrincipalCmnt principalCmnt;
}
