package org.mk.dentisteapp.services;

import org.mk.dentisteapp.entities.Demande;
import org.mk.dentisteapp.entities.Private;
import org.mk.dentisteapp.entities.Salon;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface SalonService {
    List<Private>getAll(Optional<String> query);
    boolean canEnterSalon(Long salon_id,Long dentiste_id);
    ResponseEntity<String> sendDemande(Long salon_id,Long dentiste_id);
    ResponseEntity<String> acceptOrRefuseDemande(Long salon_id,Long dentiste_id);
    ResponseEntity<String> refuseDemande(Long salon_id,Long dentiste_id);
    ResponseEntity<String> isDemandeAccepted(Long salon_id,Long dentiste_id);
    List<Demande>getAcceptedDemandes(Long id,Optional<String> query);
    List<Demande>getUnaccepted(Long id,Optional<String> query);
    Salon getOne(Long id);
    Salon addSalon(Salon salon);
    Salon edit(Salon salon);
    void delete(Long id);
}
