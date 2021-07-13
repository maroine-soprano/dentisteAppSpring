package org.mk.dentisteapp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.mk.dentisteapp.entities.Commentaire;
import org.mk.dentisteapp.entities.PrincipalCmnt;
import org.mk.dentisteapp.entities.Sujet;
import org.mk.dentisteapp.services.SujetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sujets")
public class SujetController {

    @Autowired
    SujetService sujetService;

    @GetMapping
    public List<Sujet>getAll(@RequestParam Optional<String> query){
        return sujetService.getAll(query);
    }

    @GetMapping("{categorieNom}")
    public List<Sujet>getByCategorie(@PathVariable("categorieNom") String categorieNom,@RequestParam Optional<String> query){
        return sujetService.getByCategorie(categorieNom,query);
    }

    @GetMapping("{id}/sujet")
    public Sujet getById(@PathVariable("id") Long id){
        return sujetService.getById(id);
    }

    @GetMapping("{id}/commentaires")
    public List<PrincipalCmnt>getCmnts(@PathVariable("id") Long id){
        return sujetService.getCmnts(id);
    }

    @GetMapping("/file/{filename}")
    public byte[] getFile(@PathVariable("filename") String filename){
        return sujetService.getFile(filename);
    }

    @PostMapping
    public Sujet add(@RequestParam MultipartFile file, @RequestParam String sujet,@RequestParam(required = false) String id) throws JsonProcessingException {
        return sujetService.add(file,sujet,id);
    }

    @PostMapping("{id}/changeArchive")
    public void changeArchive(@PathVariable("id") Long id){
        sujetService.changeArchive(id);
    }

    @PostMapping("{id}/changeStatut")
    public void changeStatut(@PathVariable("id") Long id){
        sujetService.changeStatut(id);
    }

    @PutMapping("{id}")
    public Sujet update(@PathVariable("id") Long id,@RequestParam(value = "file", required = false) MultipartFile file,
                        @RequestParam String sujet) throws JsonProcessingException {
        return sujetService.update(id,file,sujet);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id){
        sujetService.delete(id);
    }

    @DeleteMapping("{filename}/file")
    public void deleteFile(@PathVariable("filename") String filename){
        sujetService.deleteFile(filename);
    }
}
