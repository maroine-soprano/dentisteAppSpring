package org.mk.dentisteapp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.mk.dentisteapp.entities.Dentiste;
import org.mk.dentisteapp.services.DentisteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentistes")
public class DentisteController {

    @Autowired
    DentisteService dentisteService;

    @GetMapping
    public List<Dentiste> index(@RequestParam Optional<String> query){
        return dentisteService.getAll(query);
    }

    @GetMapping("{id}")
    public Dentiste get(@PathVariable("id") Long id) {
        return dentisteService.getOne(id);
    }

    @GetMapping(path="/{id}/photo")
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
        return dentisteService.getImage(id);
    }

    @GetMapping(path="{id}/carte")
    public byte[] getCarte(@PathVariable("id") Long id) throws Exception{
        return dentisteService.getCarte(id);
    }

    @PutMapping("{id}")
    public Long update(@PathVariable("id") Long id,@RequestBody Dentiste dentiste){
        return dentisteService.update(dentiste);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id){
        dentisteService.delete(id);
    }


}
