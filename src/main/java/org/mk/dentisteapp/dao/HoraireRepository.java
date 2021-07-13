package org.mk.dentisteapp.dao;

import org.mk.dentisteapp.entities.Horaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HoraireRepository extends JpaRepository<Horaire,Long> {
    Horaire findFirstById(Long id);
    Horaire findFirstByValueEqualsAndJourNom(String value,String jour_id);
}
