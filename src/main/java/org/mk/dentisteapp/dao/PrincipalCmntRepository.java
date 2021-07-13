package org.mk.dentisteapp.dao;

import org.mk.dentisteapp.entities.PrincipalCmnt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrincipalCmntRepository extends JpaRepository<PrincipalCmnt,Long> {
    List<PrincipalCmnt>findAllBySujetId(Long id);
    PrincipalCmnt findFirstById(Long id);
}
