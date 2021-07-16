package org.mk.dentisteapp.services;

import org.mk.dentisteapp.dao.*;
import org.mk.dentisteapp.entities.Demande;
import org.mk.dentisteapp.entities.Private;
import org.mk.dentisteapp.entities.Public;
import org.mk.dentisteapp.entities.Salon;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalonServiceImpl implements SalonService{
    @Autowired
    PublicRepository publicRepository;
    @Autowired
    PrivateRepository privateRepository;
    @Autowired
    DemandeRepository demandeRepository;
    @Autowired
    SalonRepository salonRepository;
    @Autowired
    DentisteRepository dentisteRepository;

    @Override
    public List<Private> getAll(Optional<String> query) {
        return privateRepository.findAllByActiveTrueAndNomContainingOrderByCreatedDateDesc(query.orElse(" "));
    }

    @Override
    public ResponseEntity<String> sendDemande(Long salon_id, Long dentiste_id) {
        if (demandeRepository.findFirstByDentisteIdAndPrivateSalonId(dentiste_id,salon_id)==null){
            Demande demande=new Demande();
            demande.setAccepted(false);
            demande.setDentiste(dentisteRepository.findFirstById(dentiste_id));
            demande.setPrivateSalon(privateRepository.findFirstById(salon_id));
            demandeRepository.save(demande);
            return new ResponseEntity<>("demande added", HttpStatus.OK);
        }
        return new ResponseEntity<>("demande already send", HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<String> acceptOrRefuseDemande(Long salon_id, Long dentiste_id) {
        Demande demande=demandeRepository.findFirstByDentisteIdAndPrivateSalonId(dentiste_id,salon_id);
        String val="accepted";
        if (!demande.isAccepted()){
            demande.setAccepted(true);
            demandeRepository.save(demande);
        }
        else{
            demandeRepository.deleteById(demande.getId());
            val="user has been removed";
        }
        return new ResponseEntity<>(val, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> refuseDemande(Long salon_id, Long dentiste_id) {
        Demande demande=demandeRepository.findFirstByDentisteIdAndPrivateSalonId(dentiste_id,salon_id);
        demandeRepository.deleteById(demande.getId());
        return new ResponseEntity<>("refused", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> isDemandeAccepted(Long salon_id, Long dentiste_id) {
        if (demandeRepository.findFirstByAcceptedTrueAndDentisteIdAndPrivateSalonId(dentiste_id,salon_id)==null)
            return new ResponseEntity<>("false", HttpStatus.OK);
        else return new ResponseEntity<>("true", HttpStatus.OK);
    }

    @Override
    public List<Demande> getAcceptedDemandes(Long id, Optional<String> query) {
        return demandeRepository.findAllByAcceptedTrueAndPrivateSalonIdAndDentisteNomContaining(id,query.orElse(""));
    }

    @Override
    public List<Demande> getUnaccepted(Long id, Optional<String> query) {
        return demandeRepository.findAllByAcceptedFalseAndPrivateSalonIdAndDentisteNomContaining(id,query.orElse(""));
    }

    @Override
    public Salon getOne(Long id) {
        return salonRepository.findFirstById(id);
    }

    @Override
    public Salon addSalon(Salon salon) {
        Salon newSalon;
        if (salon.getType().equals("public"))newSalon=new Public();
        else newSalon=new Private();
        BeanUtils.copyProperties(salon,newSalon);
        return salonRepository.save(newSalon);
    }

    @Override
    public Salon edit(Salon salon) {
        Salon newSalon;
        if (salon.getType().equals("public"))newSalon=new Public();
        else newSalon=new Private();
        BeanUtils.copyProperties(salon,newSalon);
        return salonRepository.save(newSalon);
    }

    @Override
    public void delete(Long id) {
        salonRepository.deleteById(id);
    }
}
