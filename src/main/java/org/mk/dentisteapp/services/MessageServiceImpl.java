package org.mk.dentisteapp.services;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.mk.dentisteapp.dao.FichierMessageRepository;
import org.mk.dentisteapp.dao.MessageRepository;
import org.mk.dentisteapp.dao.SalonRepository;
import org.mk.dentisteapp.entities.*;
import org.mk.dentisteapp.util.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
public class MessageServiceImpl implements MessageService{

    @Autowired
    ServletContext context;
    @Autowired
    SalonRepository salonRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    FichierMessageRepository fichierMessageRepository;

    @Override
    public Message addMsg(Long salon_id, MultipartFile file, String txt,String msg_id) {
        Message message=new Message();
        if (txt!=null)message.setTxt(txt);
        if (file==null){
            Salon salon=salonRepository.findFirstById(salon_id);
            UserPrincipal myUserDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user= myUserDetails.getUser();
            message.setDentiste(user.getDentiste());
            message.setSalon(salon);
            return messageRepository.save(message);
        }
        Message newMsg;
        Map<String,String>data=uploadFile(file);
        FichierMessage fichier=new FichierMessage(data.get("filename"),data.get("type"));
        message.getFichiersMessage().add(fichier);
        if (msg_id==null){
            Salon salon=salonRepository.findFirstById(salon_id);
            UserPrincipal myUserDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user= myUserDetails.getUser();
            message.setDentiste(user.getDentiste());
            message.setSalon(salon);
            fichier.setMessage(message);
            newMsg=messageRepository.save(message);
        }
        else {
            message.setId(Long.valueOf(msg_id));
            newMsg=message;
            fichier.setMessage(message);
            fichierMessageRepository.save(fichier);
        }
        return newMsg;
    }

    @Override
    public byte[] getFile(String nom) {
        try {
            return Files.readAllBytes(Paths.get(context.getRealPath("/filesMessage/")+nom));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<byte[]> getDocument(String filename) {
        byte[] contents=null;
        try {
            contents= Files.readAllBytes(Paths.get(context.getRealPath("/filesMessage/")+filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        return response;
    }

    private Map<String,String> uploadFile(MultipartFile file){
        boolean isExit = new File(context.getRealPath("/filesMessage/")).exists();
        String mimeType="";
        if (!isExit) {
            new File(context.getRealPath("/filesMessage/")).mkdir();
        }
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename) + Calendar.getInstance().getTimeInMillis()+"." + FilenameUtils.getExtension(filename);
        File serverFile = new File(context.getRealPath("/filesMessage/" + File.separator + newFileName));
        try {
            FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
            mimeType = Files.probeContentType(serverFile.toPath());

        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,String>data=new HashMap<>();
        data.put("filename",newFileName);
        data.put("type",mimeType.split("/")[0]);
        return data;
    }
}
