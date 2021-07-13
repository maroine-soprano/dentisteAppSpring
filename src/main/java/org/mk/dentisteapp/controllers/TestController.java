package org.mk.dentisteapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
