package org.mk.dentisteapp.dao;

import org.mk.dentisteapp.entities.Dentiste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DentisteRepository extends JpaRepository<Dentiste,Long> {
    Dentiste findFirstById(Long id);
    List<Dentiste>findByPrenomContainingOrNomContaining(String prenom,String nom);
    List<Dentiste>findAllByUserConfirmeAndUserActive(boolean confirme,boolean active);
    long count();
}
