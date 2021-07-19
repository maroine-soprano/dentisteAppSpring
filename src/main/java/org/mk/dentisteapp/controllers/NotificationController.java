package org.mk.dentisteapp.controllers;

import org.mk.dentisteapp.entities.Notification;
import org.mk.dentisteapp.filter.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
public class NotificationController {

    @Autowired
    NotificationService notificationService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/noti")
    public void sendSpecific(
            @Payload Notification msg){
        notificationService.add(msg);
        simpMessagingTemplate.convertAndSendToUser(
                msg.getUser_id(),"/queue/messages", msg);
    }

    @GetMapping("notifications/{userId}")
    @ResponseBody
    public List<Notification>getAllByUser(@PathVariable("userId") Long userId){
        return notificationService.getAllByUser(userId);
    }


}
