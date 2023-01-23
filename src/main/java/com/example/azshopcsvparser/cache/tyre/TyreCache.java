package com.example.azshopcsvparser.cache.tyre;

import com.example.azshopcsvparser.cache.Cachable;
import com.example.azshopcsvparser.cache.staticresources.Cache;
import com.example.azshopcsvparser.model.Tyre24Model;
import com.example.azshopcsvparser.parser.Tyre24Parser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class TyreCache {
    private static String ALL_TYRE_CACHE_NAME = "all_tyre_cache_UUID_82783363K";
    private static String ALL_TYRE_MANUFACTUERS_NAME = "all_manufacturers_cache_UUID_102923876P";

    private static Map<String, List<Tyre24Model>> indexedItems = new HashMap<>();

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
            for (Tyre24Model tyre: allTyre) {
                indexByDescription(tyre);
            }

            return true;
        }
        return false;
    }

    public List<Tyre24Model> findBy(String query){
        List<Tyre24Model> results = new ArrayList<>();
        String[] queryItems = query.split(" ", 20);
        for (String item : queryItems){
            List<Tyre24Model> collect = allTyre.stream().
                    filter(tyre24Model -> tyre24Model.getId().contains(item))
                    .collect(Collectors.toList());
            List<Tyre24Model> indexedResult = indexedItems.get(item);
            if (collect != null)
                results.addAll(collect);
            if (indexedResult != null)
                results.addAll(indexedResult);
        }

        return results.stream().distinct().collect(Collectors.toList());
    }

    public void indexByDescription(Tyre24Model tyre24Model){
        if (!(tyre24Model.getDescription() != null && !tyre24Model.getDescription().isBlank()))
                return;


        List<String> descriptionElements = Arrays.asList(tyre24Model.getDescription().split(" ", 20));
        List<String> notBlankElements = descriptionElements.stream().filter(stringElement -> !stringElement.isBlank()).collect(Collectors.toList());

        //se elemento non parte per 

        /* DEVO FARE UNA HASHMAP CONTENENTE I TAG OTTENUTI DALLO SPLIT
        Key = notBlankElement
        Value = List<Tyre24Element>
        */
        for (String e: notBlankElements) {
            if (e != null && !e.isBlank()){
                //Battistrada e spalla
                String[] split = e.split("/");
                if (split.length == 2) {
                    for (String splitValue : split) {

                        addIndexItem(splitValue, tyre24Model);
                        if (splitValue.contains("R") && !splitValue.contains("ZR")) {
                            String[] spallaECerchio = splitValue.split("R");
                            if (Arrays.stream(spallaECerchio).count() == 2) {
                                addIndexItem(spallaECerchio[0], tyre24Model);
                                addIndexItem("R" + spallaECerchio[1], tyre24Model);
                            }
                        }
                    }
                    addIndexItem(split[0] + split[1], tyre24Model);
                }
            }else if (e.length() == 3 || e.length() == 4){
                //Indice di carico e velocità
                if (Character.isDigit(e.charAt(0)) && Character.isAlphabetic(e.charAt(e.length() -1 ))){
                    System.out.println("Found index value " + e);
                    String numericIndex = e.substring(0, e.length() - 2);
                    addIndexItem(numericIndex, tyre24Model);
                    String charIndex = e.substring(e.length() - 2, e.length() - 1);
                    addIndexItem(charIndex, tyre24Model);
                }
            }
            addIndexItem(e, tyre24Model);
        }
    }

    public List<String> getAllManufacturersNames(){
        if (allManufacturersNames == null){
            allManufacturersNames = allTyre.stream()
                    .map(Tyre24Model::getManufacturer)
                    .distinct()
                    .collect(Collectors.toList());
            Cache.putCache(new Cachable(allManufacturersNames, new Date()), ALL_TYRE_MANUFACTUERS_NAME);
        }

        indexedItems.keySet().stream().count();
        return allManufacturersNames;

    }


    private static void addIndexItem(String key, Tyre24Model model){
        List<Tyre24Model> tyre24Models = indexedItems.get(key);
        if (tyre24Models == null) {
            ArrayList<Tyre24Model> values = new ArrayList<>();
            values.add(model);
            indexedItems.put(key, values);
        }else {
            long count = tyre24Models.stream().filter(indexedModel -> indexedModel.getId() == model.getId()).collect(Collectors.toList()).stream().count();
            if (count == 0) {
                tyre24Models.add(model);
            }
            indexedItems.put(key, tyre24Models);
        }

    }


}
