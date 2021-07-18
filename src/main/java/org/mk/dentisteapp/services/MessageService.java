package org.mk.dentisteapp.services;

import org.mk.dentisteapp.entities.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface MessageService {
    Message addMsg(Long salon_id, MultipartFile file,String txt,String msg_id);
    byte[] getFile(String nom);
    ResponseEntity<byte[]> getDocument(String filename);
}
