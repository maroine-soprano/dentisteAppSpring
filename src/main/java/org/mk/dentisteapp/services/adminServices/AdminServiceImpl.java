package org.mk.dentisteapp.services.adminServices;

import org.mk.dentisteapp.dao.CategorieRepository;
import org.mk.dentisteapp.dao.DentisteRepository;
import org.mk.dentisteapp.dao.SujetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    DentisteRepository dentisteRepository;
    @Autowired
    CategorieRepository categorieRepository;
    @Autowired
    SujetRepository sujetRepository;

    @Override
    public Map<String, Integer> getStats() {
        Map<String ,Integer>data=new HashMap<>();
        data.put("dentistes", Math.toIntExact(dentisteRepository.count()));
        data.put("categories", Math.toIntExact(categorieRepository.count()));
        data.put("sujets", Math.toIntExact(sujetRepository.count()));
        return data;
    }
}
