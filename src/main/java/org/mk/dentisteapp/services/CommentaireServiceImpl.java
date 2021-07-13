package org.mk.dentisteapp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.mk.dentisteapp.dao.CommentaireRepository;
import org.mk.dentisteapp.dao.FichierCmntRepository;
import org.mk.dentisteapp.dao.PrincipalCmntRepository;
import org.mk.dentisteapp.entities.*;
import org.mk.dentisteapp.util.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Service
public class CommentaireServiceImpl implements CommentaireService{

    @Autowired
    ServletContext context;
    @Autowired
    CommentaireRepository commentaireRepository;
    @Autowired
    FichierCmntRepository fichierCmntRepository;
    @Autowired
    PrincipalCmntRepository principalCmntRepository;

    private FichierCmnt addFile(MultipartFile file,Long id) {
        Map<String,String>data=uploadFile(file);
        FichierCmnt fichier=new FichierCmnt(data.get("filename"),data.get("type"));
        Commentaire cmt=commentaireRepository.findFirstById(id);
        fichier.setCommentaire(cmt);
        fichierCmntRepository.save(fichier);
        return fichier;
    }

    @Override
    public Commentaire add(MultipartFile file, String cmntString, String sujetString,String type,Long principalCmtId) throws JsonProcessingException {
        Commentaire commentaire;
        if (type.equals("principal")) commentaire= new ObjectMapper().readValue(cmntString, PrincipalCmnt.class);
        else {
            commentaire= new ObjectMapper().readValue(cmntString, SousCmnt.class);
            SousCmnt s=(SousCmnt)commentaire;
            s.setPrincipalCmnt(principalCmntRepository.findFirstById(principalCmtId));
        }
        Sujet sujet = new ObjectMapper().readValue(sujetString, Sujet.class);
        commentaire.setSujet(sujet);
        UserPrincipal myUserDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user= myUserDetails.getUser();
        commentaire.setDentiste(user.getDentiste());
        Commentaire newCmt=commentaireRepository.save(commentaire);
        if (file!=null){
            Map<String,String>data=uploadFile(file);
            FichierCmnt fichier=new FichierCmnt(data.get("filename"),data.get("type"));
            fichier.setCommentaire(newCmt);
            FichierCmnt newfichier=fichierCmntRepository.save(fichier);
            newCmt.getFichiersCmnt().add(newfichier);
        }
        return newCmt;
    }

    @Override
    public byte[] getFile(String nom) {
        try {
            return Files.readAllBytes(Paths.get(context.getRealPath("/filesCmnt/")+nom));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Map<String,String> uploadFile(MultipartFile file){
        boolean isExit = new File(context.getRealPath("/filesCmnt/")).exists();
        if (!isExit) {
            new File(context.getRealPath("/filesCmnt/")).mkdir();
        }
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename) + Calendar.getInstance().getTimeInMillis()+"." + FilenameUtils.getExtension(filename);
        File serverFile = new File(context.getRealPath("/filesCmnt/" + File.separator + newFileName));
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
