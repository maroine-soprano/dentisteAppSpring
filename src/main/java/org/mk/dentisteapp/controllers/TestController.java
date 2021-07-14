package org.mk.dentisteapp.controllers;

import org.mk.dentisteapp.services.CommentaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestController {

    @RequestMapping("/admin")
    @ResponseBody
    public String login(){
        return "admin page";
    }

    @DeleteMapping("/forum")
    @ResponseBody
    public String test(){
        return "forum deleted";
    }

    @RequestMapping("/forum")
    @ResponseBody
    public String showForum(){
        return "forum n1";
    }

    @DeleteMapping("/salons")
    @ResponseBody
    public String testB(){
        return "salon deleted";
    }

}
