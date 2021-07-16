package org.mk.dentisteapp.dao;

import org.mk.dentisteapp.entities.Private;
import org.mk.dentisteapp.entities.Public;
import org.mk.dentisteapp.entities.Salon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrivateRepository extends JpaRepository<Private,Long> {
    Private findFirstById(Long id);
    List<Private>findAllByActiveTrueOrderByCreatedDateDesc();
    List<Private>findAllByActiveTrueAndNomContainingOrderByCreatedDateDesc(String nom);
}
