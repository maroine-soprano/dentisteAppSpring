package org.mk.dentisteapp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.mk.dentisteapp.dao.DentisteRepository;
import org.mk.dentisteapp.dao.HoraireRepository;
import org.mk.dentisteapp.dao.UserRepository;
import org.mk.dentisteapp.entities.Dentiste;
import org.mk.dentisteapp.entities.Horaire;
import org.mk.dentisteapp.entities.Sujet;
import org.mk.dentisteapp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
public class DentisteServiceImpl implements DentisteService{

    @Autowired
    DentisteRepository dentisteRepository;
    @Autowired
    HoraireRepository horaireRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ServletContext context;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<Dentiste> getAll(Optional<String> query) {
        if (query.isPresent())return dentisteRepository.findByPrenomContainingOrNomContaining(query.orElse("_"),query.orElse("_"));
        return dentisteRepository.findAll();
    }

    @Override
    public ResponseEntity<String> add(MultipartFile photo, MultipartFile carte, String dentisteString,
                                      String userString) throws JsonProcessingException {
        Dentiste dentiste = new ObjectMapper().readValue(dentisteString, Dentiste.class);
        User user=new ObjectMapper().readValue(userString, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        System.out.println("////////////////"+user);
        user.setDentiste(dentiste);
        Set<Horaire>horaires=new HashSet<>();
        for (Horaire horaire : dentiste.getHoraires()) {
            Horaire existHoraire=horaireRepository.findFirstByValueEqualsAndJourNom(horaire.getValue(),horaire.getJour().getNom());
            Horaire newOne=horaire;
            if (existHoraire!=null)newOne=existHoraire;
            newOne.getDentistes().add(dentiste);
            horaires.add(newOne);
        }
        dentiste.setPhoto(uploadFile(photo));
        dentiste.setCarte(uploadFile(carte));
        dentiste.setHoraires(horaires);
        dentiste.setUser(user);
        dentisteRepository.save(dentiste);
        return new ResponseEntity<>("done", HttpStatus.OK);
    }

    @Override
    public Long update(Dentiste dentiste) {
        return dentisteRepository.save(dentiste).getId();
    }

    @Override
    public void delete(Long id) {
        dentisteRepository.deleteById(id);
    }

    @Override
    public Dentiste getOne(Long id) {
        return dentisteRepository.findFirstById(id);
    }

    @Override
    public byte[] getImage(Long id) {
        Dentiste dentiste   = dentisteRepository.findFirstById(id);
        try {
            return Files.readAllBytes(Paths.get(context.getRealPath("/filesDentiste/")+dentiste.getPhoto()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public byte[] getCarte(Long id) {
        Dentiste dentiste   = dentisteRepository.findFirstById(id);
        try {
            return Files.readAllBytes(Paths.get(context.getRealPath("/filesDentiste/")+dentiste.getCarte()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String uploadFile(MultipartFile file){
        boolean isExit = new File(context.getRealPath("/filesDentiste/")).exists();
        if (!isExit) {
            new File(context.getRealPath("/filesDentiste/")).mkdir();
        }
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename) + Calendar.getInstance().getTimeInMillis()+"." + FilenameUtils.getExtension(filename);
        File serverFile = new File(context.getRealPath("/filesDentiste/" + File.separator + newFileName));
        try {
            FileUtils.writeByteArrayToFile(serverFile, file.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return newFileName;
    }
}
