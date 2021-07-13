package org.mk.dentisteapp.dao;

import org.mk.dentisteapp.entities.Sujet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SujetRepository extends JpaRepository<Sujet,Long> {
    Sujet findFirstById(Long id);
    List<Sujet>findAllByArchiveAndActiveTrueOrderByCreatedDateDesc(boolean archive);
    List<Sujet>findAllByArchiveAndActiveTrueAndDescriptionContainingOrderByCreatedDateDesc(boolean archive,String titre);
    List<Sujet>findAllByCategorieNomAndArchiveAndActiveTrueOrderByCreatedDateDesc(String nom,boolean archive);
    List<Sujet>findAllByCategorieNomAndArchiveAndActiveTrueAndDescriptionContainingOrderByCreatedDateDesc(String nom,boolean archive,String titre);
    long count();
}
