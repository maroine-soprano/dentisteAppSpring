package org.mk.dentisteapp.dao;

import org.mk.dentisteapp.entities.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentaireRepository extends JpaRepository<Commentaire,Long> {
    Commentaire findFirstById(Long id);
}
