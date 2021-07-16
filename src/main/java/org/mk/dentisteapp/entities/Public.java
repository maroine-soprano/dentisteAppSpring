package org.mk.dentisteapp.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue( value="public" )
public class Public extends Salon{
}
