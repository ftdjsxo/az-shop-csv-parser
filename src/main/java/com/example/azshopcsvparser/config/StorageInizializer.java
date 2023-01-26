package com.example.azshopcsvparser.config;

import com.example.azshopcsvparser.model.Tyre24Model;
import com.example.azshopcsvparser.parser.Tyre24Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;

@Component
public class StorageInizializer {

    Logger logger = LoggerFactory.getLogger(StorageInizializer.class);


/*    @PostConstruct
    public void initializeStorage() throws Exception {
        Tyre24Parser.toWixCSV();
        *//*
        ArrayList<Tyre24Model> tyre24Models;
        try {
            tyre24Models = Tyre24Parser.scanLocalCSV();
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
        logger.info("Successfull parsed {} tyres from Tyre24 CSV", tyre24Models.size());
        repository.deleteAll();
        repository.insert(tyre24Models);
        logger.info("Successfull insert {} tyres into repo", repository.count());*//*

    }*/

}
