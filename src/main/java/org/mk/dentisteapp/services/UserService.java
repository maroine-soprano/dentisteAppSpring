package org.mk.dentisteapp.services;

import org.mk.dentisteapp.entities.Dentiste;
import org.mk.dentisteapp.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User getCurrent();
    List<User> getDemandes(Optional<String> query);
    void changeConfirme(Long id);
    void changeStatut(Long id);
}
