package org.mk.dentisteapp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.FileUtils;
import org.mk.dentisteapp.dao.FichierSujetRepository;
import org.mk.dentisteapp.dao.PrincipalCmntRepository;
import org.mk.dentisteapp.dao.SujetRepository;
import org.mk.dentisteapp.entities.*;
import org.mk.dentisteapp.util.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
public class SujetServiceImpl implements SujetService{

    @Autowired
    SujetRepository sujetRepository;
    @Autowired
    ServletContext context;
    @Autowired
    FichierSujetRepository fichierSujetRepository;
    @Autowired
    PrincipalCmntRepository principalCmntRepository;

    @Override
    public List<Sujet> getAll(Optional<String> query) {
        List<Sujet> data;
        if (query.isPresent()){
            data=sujetRepository.findAllByArchiveAndActiveTrueAndDescriptionContainingOrderByCreatedDateDesc(true,query.orElse("_"));
            data.addAll(sujetRepository.findAllByArchiveAndActiveTrueAndDescriptionContainingOrderByCreatedDateDesc(false,query.orElse("_")));
        }
        else{
            data=sujetRepository.findAllByArchiveAndActiveTrueOrderByCreatedDateDesc(true);
            data.addAll(sujetRepository.findAllByArchiveAndActiveTrueOrderByCreatedDateDesc(false));
        }
        return data;
    }

    @Override
    public List<Sujet> getByCategorie(String nomCategorie, Optional<String> query) {
        List<Sujet> data;
        if (query.isPresent()){
            data=sujetRepository.findAllByCategorieNomAndArchiveAndActiveTrueAndDescriptionContainingOrderByCreatedDateDesc(nomCategorie,true,query.orElse("_"));
            data.addAll(sujetRepository.findAllByCategorieNomAndArchiveAndActiveTrueAndDescriptionContainingOrderByCreatedDateDesc(nomCategorie,false,query.orElse("_")));
        }
        else{
            data=sujetRepository.findAllByCategorieNomAndArchiveAndActiveTrueOrderByCreatedDateDesc(nomCategorie,true);
            data.addAll(sujetRepository.findAllByCategorieNomAndArchiveAndActiveTrueOrderByCreatedDateDesc(nomCategorie,false));
        }
        return data;
    }

    @Override
    public Sujet getById(Long id) {
        return sujetRepository.findFirstById(id);
    }

    @Override
    public List<PrincipalCmnt> getCmnts(Long id) {
        return principalCmntRepository.findAllBySujetId(id);
    }

    @Override
    public byte[] getFile(String filename) {

        try {
            return Files.readAllBytes(Paths.get(context.getRealPath("/filesSujet/")+filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Sujet add(MultipartFile file, String sujetString,String id) throws JsonProcessingException {
        Sujet sujet = new ObjectMapper().readValue(sujetString, Sujet.class);
        Sujet newSujet=sujet;
        Map<String,String>data=uploadFile(file);
        FichierSujet fichier=new FichierSujet(data.get("filename"),data.get("type"));
        sujet.getFichiersSujet().add(fichier);
        if (id==null) {
            UserPrincipal myUserDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user= myUserDetails.getUser();
            sujet.setDentiste(user.getDentiste());
            fichier.setSujet(sujet);
            newSujet=sujetRepository.save(sujet);
        }
        else {
            sujet.setId(Long.valueOf(id));
            fichier.setSujet(sujet);
            fichierSujetRepository.save(fichier);
        }
        return newSujet;
    }

    @Override
    public void changeStatut(Long id) {
        Sujet sujet=sujetRepository.findFirstById(id);
        sujet.setActive(!(sujet.isActive()));
        sujetRepository.save(sujet);
    }

    @Override
    public void changeArchive(Long id) {
        Sujet sujet=sujetRepository.findFirstById(id);
        sujet.setArchive(!(sujet.isArchive()));
        sujetRepository.save(sujet);
    }

    @Override
    public Sujet update(Long id,MultipartFile file, String sujetString) throws JsonProcessingException {
        Sujet sujet=sujetRepository.findFirstById(id);
        Sujet oldsujet = new ObjectMapper().readValue(sujetString, Sujet.class);
        if (file!=null) {
            Map<String, String> data = uploadFile(file);
            FichierSujet fichier = new FichierSujet(data.get("filename"), data.get("type"));
            fichier.setSujet(sujet);
            sujet.getFichiersSujet().add(fichier);
        }
        sujet.setTitre(oldsujet.getTitre());
        sujet.setDescription(oldsujet.getDescription());
        sujet.setCategorie(oldsujet.getCategorie());
        return sujetRepository.save(sujet);
    }

    @Override
    public void delete(Long id) {
        sujetRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteFile(String filename) {
        System.out.println(fichierSujetRepository.findFirstByChemin(filename).getChemin());
        fichierSujetRepository.deleteByChemin(filename);
    }

    private Map<String,String> uploadFile(MultipartFile file){
        boolean isExit = new File(context.getRealPath("/filesSujet/")).exists();
        if (!isExit) {
            new File(context.getRealPath("/filesSujet/")).mkdir();
        }
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename) + Calendar.getInstance().getTimeInMillis()+"." + FilenameUtils.getExtension(filename);
        File serverFile = new File(context.getRealPath("/filesSujet/" + File.separator + newFileName));
        try {
            FileUtils.writeByteArrayToFile(serverFile, file.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,String>data=new HashMap<>();
        data.put("filename",newFileName);
        data.put("type",FilenameUtils.getExtension(filename));
        return data;
    }
}
