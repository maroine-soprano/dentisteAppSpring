package org.mk.dentisteapp.dao;

import org.mk.dentisteapp.entities.FichierMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FichierMessageRepository extends JpaRepository<FichierMessage,Long> {
}
