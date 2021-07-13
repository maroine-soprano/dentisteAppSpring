package org.mk.dentisteapp.controllers.adminControllers;

import org.mk.dentisteapp.services.adminServices.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/stats")
    public Map<String,Integer>getStats(){
        return adminService.getStats();
    }
}
