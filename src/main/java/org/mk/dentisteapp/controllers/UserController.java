package org.mk.dentisteapp.controllers;

import org.mk.dentisteapp.entities.Dentiste;
import org.mk.dentisteapp.entities.User;
import org.mk.dentisteapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("current")
    public User getCurrent(){
        return userService.getCurrent();
    }

    @GetMapping("/demandes")
    public List<User> getDemandes(@RequestParam Optional<String> query){
        return userService.getDemandes(query);
    }

    @PostMapping("/{id}/changeConfirme")
    public void changeConfirm(@PathVariable("id") Long id){
        userService.changeConfirme(id);
    }

    @PostMapping("/{id}/changeStatut")
    public void changeStatut(@PathVariable("id") Long id){
        userService.changeStatut(id);
    }
}
