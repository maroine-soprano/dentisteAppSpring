package org.mk.dentisteapp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.mk.dentisteapp.entities.Commentaire;
import org.mk.dentisteapp.entities.FichierCmnt;
import org.mk.dentisteapp.entities.PrincipalCmnt;
import org.mk.dentisteapp.entities.data.WebSocketCmnt;
import org.mk.dentisteapp.services.CommentaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CommentaireController {

    @Autowired
    CommentaireService commentaireService;

    @MessageMapping("/resume")
    @SendTo("/start/initial")
    public WebSocketCmnt chat(@Payload WebSocketCmnt cmt) {
        System.out.println(cmt.getId()+"///////////////////////////////");
        return cmt;
    }

    @PostMapping("commentaire/add")
    @ResponseBody
    public Commentaire add(@RequestParam(value = "file", required = false) MultipartFile file,
                           @RequestParam String cmnt,@RequestParam String sujet,@RequestParam String type,
                           @RequestParam(value = "principalCmt", required = false) Long principalCmt) throws JsonProcessingException {
        return commentaireService.add(file,cmnt,sujet,type,principalCmt);
    }

    @GetMapping("commentaire/file/{filename}")
    @ResponseBody
    public byte[] getFile(@PathVariable("filename") String filename){
        return commentaireService.getFile(filename);
    }

}
