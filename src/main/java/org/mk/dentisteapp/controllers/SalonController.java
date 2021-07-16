package org.mk.dentisteapp.controllers;

import org.mk.dentisteapp.entities.Demande;
import org.mk.dentisteapp.entities.Dentiste;
import org.mk.dentisteapp.entities.Private;
import org.mk.dentisteapp.entities.Salon;
import org.mk.dentisteapp.services.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/salons")
public class SalonController {
    @Autowired
    SalonService salonService;

    @GetMapping
    public List<Private>getAll(@RequestParam Optional<String> query){
        return salonService.getAll(query);
    }

    @GetMapping("{id}/acceptedDemandes")
    public List<Demande> getAcceptedDemandes(@PathVariable("id") Long id, @RequestParam Optional<String> query){
        return salonService.getAcceptedDemandes(id,query);
    }

    @GetMapping("{id}/unaccepted")
    public List<Demande> getUnaccepted(@PathVariable("id") Long id, @RequestParam Optional<String> query){
        return salonService.getUnaccepted(id,query);
    }

    @PostMapping("{id}/sendDemande/{id_dentiste}")
    public ResponseEntity<String>sendDemande(@PathVariable("id") Long id
            ,@PathVariable("id_dentiste") Long id_dentiste){
        return salonService.sendDemande(id,id_dentiste);
    }

    @PostMapping("{id}/changeDemande/{id_dentiste}")
    public ResponseEntity<String>acceptOrRefuseDemande(@PathVariable("id") Long id
            ,@PathVariable("id_dentiste") Long id_dentiste){
        return salonService.acceptOrRefuseDemande(id,id_dentiste);
    }

    @PostMapping("{id}/refuseDemande/{id_dentiste}")
    public ResponseEntity<String>refuseDemande(@PathVariable("id") Long id
            ,@PathVariable("id_dentiste") Long id_dentiste){
        return salonService.refuseDemande(id,id_dentiste);
    }

    @GetMapping("{id}/demande/{id_dentiste}")
    public ResponseEntity<String> isDemandeAccepted(@PathVariable("id") Long id
            ,@PathVariable("id_dentiste") Long id_dentiste){
        return salonService.isDemandeAccepted(id,id_dentiste);
    }

    @GetMapping("{id}")
    public Salon getOne(@PathVariable("id") Long id){
        return salonService.getOne(id);
    }

    @PostMapping
    public Salon add(@RequestBody Salon salon){
        return salonService.addSalon(salon);
    }

    @PutMapping("{id}")
    public Salon edit(@PathVariable("id") Long id,@RequestBody Salon salon){
        return salonService.edit(salon);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id){
        salonService.delete(id);
    }

}
