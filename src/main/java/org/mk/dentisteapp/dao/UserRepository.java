package org.mk.dentisteapp.dao;

import org.mk.dentisteapp.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    Page<User> findAllByRole_RoleName(String role_name, Pageable pageable);
    Page<User> findAll(Pageable pageable);
    User findFirstByEmail(String email);
    User findFirstByActiveAndConfirmeAndEmail(boolean active,boolean confirme,String email);
    User findFirstById(Long id);
    List<User>findAllByConfirmeAndActive(boolean confirme,boolean active);
    List<User>findAllByConfirmeAndActiveAndDentisteNomContainingOrConfirmeAndActiveAndDentistePrenomContaining(
            boolean confirme, boolean active, String dentiste_nom, boolean confirme2, boolean active2, String dentiste_prenom);
}
