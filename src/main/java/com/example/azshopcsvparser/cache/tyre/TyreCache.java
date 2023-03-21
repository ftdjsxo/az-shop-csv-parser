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
        //List<Tyre24Model> results = new ArrayList<>();

        HashMap<Tyre24Model, List<String>> resultKeysMap = new HashMap<>();

        String[] queryItems = query.split(" ", 20);
        for (String item : queryItems){
            List<Tyre24Model> collect = null;
            if (item.length() == 7)
                collect = allTyre.stream().
                        filter(tyre24Model -> tyre24Model.getArticle_id().contains(item))
                        .collect(Collectors.toList());

            List<Tyre24Model> indexedResult = indexedItems.get(item);

            //ID Results
            if (collect != null) {
                for (Tyre24Model collectedItem : collect) {
                    List<String> strings = resultKeysMap.get(collectedItem);
                    if (strings == null)
                        strings = new ArrayList<>();

                    strings.add(item);
                    resultKeysMap.put(collectedItem, strings);
                }
            }

            //inexedItemResults
            if (indexedResult != null){
                for (Tyre24Model indexedItem : indexedResult) {
                    List<String> strings = resultKeysMap.get(indexedItem);
                    if (strings == null)
                        strings = new ArrayList<>();
                    strings.add(item);
                    resultKeysMap.put(indexedItem, strings);
                }

                //results.addAll(indexedResult);
            }

        }

        ArrayList<Tyre24Model> tyre24Results = new ArrayList<>();

        Set<Map.Entry<Tyre24Model, List<String>>> entries = resultKeysMap.entrySet();

        for (Map.Entry<Tyre24Model, List<String>> entry: entries){
            if (entry.getValue().stream().count() >= Arrays.stream(queryItems).count())
                tyre24Results.add(entry.getKey());

        }

        //tyre24Results.stream().limit(30).collect(Collectors.toUnmodifiableList());
        return tyre24Results.stream().limit(30).collect(Collectors.toUnmodifiableList());
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

                if (e.length() == 3 || e.length() == 4){
                    //Indice di carico e velocità
                    if (Character.isAlphabetic(e.charAt(0)) && Character.isDigit(e.charAt(e.length() -1 ))){
                        //System.out.println("Found index value " + e);
                        String numericIndex = e.substring(1, e.length());
                        addIndexItem(numericIndex, tyre24Model);
                        String charIndex = e.substring(0, e.length() - 2);
                        addIndexItem(charIndex, tyre24Model);
                    }
                }
            }
            addIndexItem(e, tyre24Model);
            addIndexItem(tyre24Model.getManufacturer(), tyre24Model);
            //addIndexItem(tyre24Model.get);
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
