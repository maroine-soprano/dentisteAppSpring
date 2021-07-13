package org.mk.dentisteapp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.mk.dentisteapp.entities.Dentiste;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface DentisteService {
    List<Dentiste>getAll(Optional<String> query);
    ResponseEntity<String>add(MultipartFile photo,
                              MultipartFile carte, String dentiste,
                              String user) throws JsonProcessingException;
    Long update(Dentiste dentiste);
    void delete(Long id);
    Dentiste getOne(Long id);
    byte[] getImage(Long id);
    byte[] getCarte(Long id);
}
