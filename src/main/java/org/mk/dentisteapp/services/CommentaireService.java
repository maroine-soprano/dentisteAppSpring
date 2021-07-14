package org.mk.dentisteapp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.mk.dentisteapp.entities.Commentaire;
import org.mk.dentisteapp.entities.FichierCmnt;
import org.springframework.web.multipart.MultipartFile;

public interface CommentaireService {
    Commentaire add(MultipartFile file, String cmnt, String sujet,String type,Long principalCmt) throws JsonProcessingException;
    byte[] getFile(String nom);
    Commentaire update(Long id,Commentaire commentaire);
    void delete(Long id);
}
