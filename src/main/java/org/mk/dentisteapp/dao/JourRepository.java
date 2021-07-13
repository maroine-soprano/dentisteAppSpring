package org.mk.dentisteapp.dao;

import org.mk.dentisteapp.entities.Jour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "jours", path = "jours")
public interface JourRepository extends JpaRepository<Jour,Long> {
}
