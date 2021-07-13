package org.mk.dentisteapp.dao;

import org.mk.dentisteapp.entities.Salon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalonRepository extends JpaRepository<Salon,Long> {
}
