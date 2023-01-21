package com.example.azshopcsvparser.controller;

import com.example.azshopcsvparser.cache.tyre.TyreCache;
import com.example.azshopcsvparser.model.Tyre24Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;

@RestController
public class GommeController {

    @Autowired
    TyreCache tyreCache;

    @GetMapping("/all")
    List<Tyre24Model> all() {
        try {
            return tyreCache.getAllTyre();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("findBy")
    List<Tyre24Model> byName(@RequestParam String name) {
        return tyreCache.findByName(name);
    }

    @GetMapping("/manufacturers")
    List<String> manufacturers() {
        return tyreCache.getAllManufacturersNames();
    }

}
