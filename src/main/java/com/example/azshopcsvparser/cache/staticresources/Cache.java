package com.example.azshopcsvparser.cache.staticresources;

import com.example.azshopcsvparser.cache.Cachable;

import java.util.HashMap;
import java.util.Map;

public class Cache {

    private static Map<String, Cachable> cache = new HashMap<>();


    private Cache(){}

    synchronized static public Cachable getCacheByName(String name){
        return cache.get(name);
    }

    synchronized static public boolean putCache(Cachable cachable, String name) {
        if (name == null || name.isBlank())
            return false;
        cache.put(name, cachable);
        return true;
    }
}
