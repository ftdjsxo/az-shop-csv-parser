package com.example.azshopcsvparser.controller;

import com.example.azshopcsvparser.cache.Cachable;
import com.example.azshopcsvparser.cache.staticresources.Cache;
import com.example.azshopcsvparser.cache.tyre.TyreCache;
import com.example.azshopcsvparser.model.SetUrlRequest;
import com.example.azshopcsvparser.model.Tyre24Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


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
    List<Tyre24Model> byName(@RequestParam String q) {
        return tyreCache.findBy(q);
    }

    @GetMapping("/manufacturers")
    List<String> manufacturers() {
        return tyreCache.getAllManufacturersNames();
    }


    @GetMapping("/azredirect")
    public RedirectView redirectWithUsingRedirectView(
            RedirectAttributes attributes) {
        return new RedirectView((String) Cache.getCacheByName("RedirectCacheName").getElement());
    }

    @PostMapping ("/setRedirectUrl")
    public ResponseEntity<SetUrlRequest> setUrlRequest(@RequestBody SetUrlRequest request){
        if (request.getValue() != null) {
            Cachable<String> stringCachable = new Cachable<>(request.getValue(), new Date());
            Cache.putCache(stringCachable, "RedirectCacheName");
            return new ResponseEntity<>(request, HttpStatus.OK);
        }

        return new ResponseEntity<>(request, HttpStatus.BAD_REQUEST);
    }

}
