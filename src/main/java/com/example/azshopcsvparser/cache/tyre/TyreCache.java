package com.example.azshopcsvparser.cache.tyre;

import com.example.azshopcsvparser.cache.Cachable;
import com.example.azshopcsvparser.cache.staticresources.Cache;
import com.example.azshopcsvparser.model.Tyre24Model;
import com.example.azshopcsvparser.parser.Tyre24Parser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TyreCache {
    private static String ALL_TYRE_CACHE_NAME = "all_tyre_cache_UUID_82783363K";
    private static String ALL_TYRE_MANUFACTUERS_NAME = "all_manufacturers_cache_UUID_102923876P";

    private List<Tyre24Model> allTyre;
    private List<String> allManufacturersNames;

    public List<Tyre24Model> getAllTyre() throws Exception {
        if (allTyre == null){
            buildCacheFromCSV();
            allTyre = (List<Tyre24Model>) Cache.getCacheByName(ALL_TYRE_CACHE_NAME).getElement();
        }
        return allTyre;
    }

    private boolean buildCacheFromCSV() throws Exception {
        ArrayList<Tyre24Model> tyre24Models = Tyre24Parser.scanLocalCSV();
        return setTyreCache(tyre24Models);
    }

    private boolean setTyreCache(List<Tyre24Model> tyres){
        if (Cache.putCache(new Cachable<List<Tyre24Model>>(tyres, new Date()), ALL_TYRE_CACHE_NAME)){
            allTyre = tyres;
            return true;
        }
        return false;
    }

    public List<Tyre24Model> findByName(String name){
        return allTyre.stream().
                filter(tyre24Model -> tyre24Model.getDescription().contains(name))
                .collect(Collectors.toList());
    }

    public List<String> getAllManufacturersNames(){
        if (allManufacturersNames == null){
            allManufacturersNames = allTyre.stream()
                    .map(Tyre24Model::getManufacturer)
                    .distinct()
                    .collect(Collectors.toList());
            Cache.putCache(new Cachable(allManufacturersNames, new Date()), ALL_TYRE_MANUFACTUERS_NAME);
        }
        return allManufacturersNames;

    }


}
