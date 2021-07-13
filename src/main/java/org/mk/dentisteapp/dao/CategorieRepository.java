package org.mk.dentisteapp.dao;

import org.mk.dentisteapp.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
public interface CategorieRepository extends JpaRepository<Categorie,String> {
    long count();
    List<Categorie> findByNomContaining(String nom);
}

