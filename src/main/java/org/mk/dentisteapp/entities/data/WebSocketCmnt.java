package org.mk.dentisteapp.entities.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.mk.dentisteapp.entities.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class WebSocketCmnt {
    private Long id;//=index whene request is edit or delete
    private String text;//value
    private String author;
    private Long authorId;
    private Dentiste dentiste;
    private Sujet sujet;
    private Set<FichierCmnt> fichiersCmnt=new HashSet<>();
    private Set<SousCmnt> sousCmnts=new HashSet<>();
    private int principalCmntId;
    private Date createdDate;
    private int principalCmntIndex;//principalIndex
    private String requestType;
}
