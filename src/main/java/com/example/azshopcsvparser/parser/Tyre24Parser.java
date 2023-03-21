package com.example.azshopcsvparser.parser;


import com.example.azshopcsvparser.model.Tyre24Model;
import com.example.azshopcsvparser.model.WixModel;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Component;

import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class Tyre24Parser {

    static private List<String> allowedManufacturers = new ArrayList<>();

    static {{
        allowedManufacturers.add("MICHELIN");
        allowedManufacturers.add("TRACMAX");
        allowedManufacturers.add("HANKOOK");
        allowedManufacturers.add("NOKIAN");
        allowedManufacturers.add("FALKEN");
        allowedManufacturers.add("DUNLOP");
        allowedManufacturers.add("NEXEN");
        allowedManufacturers.add("YOKOHAMA");
        allowedManufacturers.add("PIRELLI");
        allowedManufacturers.add("FIRESTONE");
        allowedManufacturers.add("KLEBER");
        allowedManufacturers.add("BRIDGESTONE");
        allowedManufacturers.add("NANKANG");
        allowedManufacturers.add("TOYO");
    }}

    public static ArrayList<Tyre24Model> scanLocalCSV() throws Exception{
        ArrayList<Tyre24Model> tyre24Models = new ArrayList<>();

        //File testFile = new File("D:/demo_it.csv");
        File prodFile = new File("src/main/resources/prod.csv");
        //File prodFile = new File("D:/prod.csv");

        CsvMapper csvMapper = new CsvMapper();

        CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator('|').withoutQuoteChar();
        MappingIterator<Tyre24Model> tyre24ModelMappingIterator = csvMapper.readerFor(Tyre24Model.class).with(schema).readValues(prodFile);
        while (tyre24ModelMappingIterator.hasNext()) {
            Tyre24Model tyre24Model = tyre24ModelMappingIterator.nextValue();
            Double availability;
                    try {
                        availability = Double.valueOf(tyre24Model.getAvailability());
                    }catch (NumberFormatException e){
                        availability = 0d;
                    }

            boolean isAuto = !(tyre24Model.getArticle_id().startsWith("M") || tyre24Model.getArticle_id().startsWith("A"));
                    boolean isNotOldDot = !(tyre24Model.getDescription_2().contains("DOT"));

            if (tyre24Model.getManufacturer() != null  && allowedManufacturers.contains(tyre24Model.getManufacturer()) && availability >=12 && isAuto && isNotOldDot)
                tyre24Models.add(tyre24Model);
        }

        return tyre24Models;
    }

    private static List<WixModel> mapTyre24ToWix(List<Tyre24Model>tyre24models){
        ArrayList<WixModel> wixModels = new ArrayList<>();
        for (Tyre24Model tyre: tyre24models) {
            WixModel wixProduct = new WixModel(
                    tyre.getId(),
                    "Product",
                    tyre.getDescription(),
                    tyre.getDescription_2(),
                    tyre.getImage(),
                    tyre.getDescription_2(),
                    tyre.getArticle_id(),
                    "",
                    tyre.getPrice(),
                    "",
                    "TRUE",
                    "",
                    "",
                    tyre.getAvailability(),
                    "",
                    "",
                    "",
                    "",
                    tyre.getManufacturer());
            wixModels.add(wixProduct);
        }
        return wixModels;
    }

    public static void toWixCSV() throws Exception {
        File wixCSV = new File("src/main/resources/wix.csv");
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        CsvSchema schema = buildCsvHeaders("handleId", "fieldType", "name", "description", "productImageUrl", "collection", "sku", "ribbon", "price", "surcharge", "visible", "discountMode",
                "discountValue", "inventory", "weight", "cost", "productOptionName1", "productOptionType1", "productOptionDescription1", "productOptionName2", "productOptionType2",
                "productOptionDescription2", "productOptionName3", "productOptionType3", "productOptionDescription3", "productOptionName4", "productOptionType4",
                "productOptionDescription4", "productOptionName5", "productOptionType5", "productOptionDescription5", "productOptionName6", "productOptionType6",
                "productOptionDescription6", "additionalInfoTitle1", "additionalInfoDescription1", "additionalInfoTitle2", "additionalInfoDescription2", "additionalInfoTitle3",
                "additionalInfoDescription3", "additionalInfoTitle4", "additionalInfoDescription4", "additionalInfoTitle5", "additionalInfoDescription5", "additionalInfoTitle6",
                "additionalInfoDescription6", "customTextField1", "customTextCharLimit1", "customTextMandatory1", "brand");
        schema.withColumnSeparator(',');
        ObjectWriter writer = csvMapper.writerFor(WixModel.class).with(schema);
        writer.writeValues(wixCSV).writeAll(mapTyre24ToWix(scanLocalCSV()));
        System.out.println("Users saved to csv file under path: ");
        System.out.println(wixCSV);
    }

    private static CsvSchema buildCsvHeaders(String... columns ) {
        CsvSchema.Builder builder = CsvSchema.builder();
        for (String colName: columns)
            builder.addColumn(colName);

        return builder.setUseHeader(true).build();
    }
}
