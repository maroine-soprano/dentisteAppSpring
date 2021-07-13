package org.mk.dentisteapp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.mk.dentisteapp.entities.Commentaire;
import org.mk.dentisteapp.entities.PrincipalCmnt;
import org.mk.dentisteapp.entities.Sujet;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface SujetService {
    List<Sujet> getAll(Optional<String> query);
    List<Sujet> getByCategorie(String nom, Optional<String> query);
    Sujet getById(Long id);
    List<PrincipalCmnt> getCmnts(Long id);
    byte[] getFile(String filename);
    Sujet add(MultipartFile file,String sujet,String id) throws JsonProcessingException;
    void changeStatut(Long id);
    void changeArchive(Long id);
    Sujet update(Long id,MultipartFile file, String sujet) throws JsonProcessingException;
    void delete(Long id);
    void deleteFile(String filename);
}
