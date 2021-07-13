package org.mk.dentisteapp.services;

import org.mk.dentisteapp.dao.UserRepository;
import org.mk.dentisteapp.entities.Dentiste;
import org.mk.dentisteapp.entities.User;
import org.mk.dentisteapp.util.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User getCurrent() {
        UserPrincipal myUserDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return myUserDetails.getUser();
    }

    @Override
    public List<User> getDemandes(Optional<String> query) {
        if (query.isPresent()) return userRepository
                .findAllByConfirmeAndActiveAndDentisteNomContainingOrConfirmeAndActiveAndDentistePrenomContaining(false,true,query.orElse("_"),false,true,query.orElse("_"));
        return userRepository.findAllByConfirmeAndActive(false,true);
    }

    @Override
    public void changeConfirme(Long id) {
        User user=userRepository.findFirstById(id);
        user.setConfirme(!(user.isConfirme()));
        userRepository.save(user);
    }

    @Override
    public void changeStatut(Long id) {
        User user=userRepository.findFirstById(id);
        user.setActive(!(user.isActive()));
        userRepository.save(user);
    }
}
