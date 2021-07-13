package org.mk.dentisteapp.dao;

import org.mk.dentisteapp.entities.Demande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeRepository extends JpaRepository<Demande,Long> {
}
