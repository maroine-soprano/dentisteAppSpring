package org.mk.dentisteapp.dao;

import org.mk.dentisteapp.entities.Salon;
import org.mk.dentisteapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalonRepository extends JpaRepository<Salon,Long> {
    Salon findFirstById(Long id);
}
