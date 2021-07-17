package org.mk.dentisteapp.controllers;

import org.mk.dentisteapp.entities.Message;
import org.mk.dentisteapp.entities.data.WebSocketCmnt;
import org.mk.dentisteapp.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {

    @MessageMapping("/resume.message")
    @SendTo("/start/listenerAddMessage")
    public Message editOrUpdate(@Payload Message message) {
        System.out.println(message.getId()+"///////////////////////////////editOrUpdate");
        return message;
    }

    @Autowired
    MessageService messageService;

    @GetMapping("messages/file/{filename}")
    @ResponseBody
    public byte[] getFile(@PathVariable("filename") String filename){
        return messageService.getFile(filename);
    }
}
