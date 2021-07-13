package org.mk.dentisteapp.dao;

import org.mk.dentisteapp.entities.FichierSujet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FichierSujetRepository extends JpaRepository<FichierSujet,Long> {
    FichierSujet findFirstByChemin(String chemin);
    void deleteByChemin(String chemin);
}
