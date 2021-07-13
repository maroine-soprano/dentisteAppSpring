package org.mk.dentisteapp.dao;

import org.mk.dentisteapp.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findFirstByRoleName(String role);
}
