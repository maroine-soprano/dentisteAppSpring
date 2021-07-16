package org.mk.dentisteapp.dao;

import org.mk.dentisteapp.entities.Demande;
import org.mk.dentisteapp.entities.Dentiste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandeRepository extends JpaRepository<Demande,Long> {
    Demande findFirstByDentisteIdAndPrivateSalonId(Long dentiste_id,Long privateSalon_id);
    Demande findFirstByAcceptedTrueAndDentisteIdAndPrivateSalonId(Long dentiste_id,Long privateSalon_id);
    List<Demande>findAllByAcceptedTrueAndPrivateSalonIdAndDentisteNomContaining(Long salon_id, String nom);
    List<Demande>findAllByAcceptedFalseAndPrivateSalonIdAndDentisteNomContaining(Long salon_id, String nom);
}
