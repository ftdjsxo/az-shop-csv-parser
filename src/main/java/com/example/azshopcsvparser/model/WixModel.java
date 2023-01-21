package com.example.azshopcsvparser.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"handleId","fieldType","name","description","productImageUrl","collection","sku","ribbon","price","surcharge","visible","discountMode",
        "discountValue","inventory","weight","cost","productOptionName1","productOptionType1","productOptionDescription1","productOptionName2","productOptionType2",
        "productOptionDescription2","productOptionName3","productOptionType3","productOptionDescription3","productOptionName4","productOptionType4",
        "productOptionDescription4","productOptionName5","productOptionType5","productOptionDescription5","productOptionName6","productOptionType6",
        "productOptionDescription6","additionalInfoTitle1","additionalInfoDescription1","additionalInfoTitle2","additionalInfoDescription2","additionalInfoTitle3",
        "additionalInfoDescription3","additionalInfoTitle4","additionalInfoDescription4","additionalInfoTitle5","additionalInfoDescription5","additionalInfoTitle6",
        "additionalInfoDescription6","customTextField1","customTextCharLimit1","customTextMandatory1","brand"})
public class WixModel{
    private String handleId;
    private String fieldType;
    private String name;
    private String description;
    private String productImageUrl;
    private String collection;
    private String sku;
    private String ribbon;
    private String price;
    private String surcharge;
    private String visible;
    private String discountMode;
    private String discountValue;
    private String inventory;
    private String weight;
    private String cost;
    private String productOptionName1;
    private String productOptionType1;
    private String productOptionDescription1;
    private String productOptionName2;
    private String productOptionType2;
    private String productOptionDescription2;
    private String productOptionName3;
    private String productOptionType3;
    private String productOptionDescription3;
    private String productOptionName4;
    private String productOptionType4;
    private String productOptionDescription4;
    private String productOptionName5;
    private String productOptionType5;
    private String productOptionDescription5;
    private String productOptionName6;
    private String productOptionType6;
    private String productOptionDescription6;
    private String additionalInfoTitle1;
    private String additionalInfoDescription1;
    private String additionalInfoTitle2;
    private String additionalInfoDescription2;
    private String additionalInfoTitle3;
    private String additionalInfoDescription3;
    private String additionalInfoTitle4;
    private String additionalInfoDescription4;
    private String additionalInfoTitle5;
    private String additionalInfoDescription5;
    private String additionalInfoTitle6;
    private String additionalInfoDescription6;
    private String customTextField1;
    private String customTextCharLimit1;
    private String customTextMandatory1;
    private String brand;

    public WixModel(String handleId, String fieldType, String name, String description, String productImageUrl, String collection, String sku, String ribbon, String price, String surcharge, String visible, String discountMode, String discountValue, String inventory, String weight, String cost, String additionalInfoTitle1, String additionalInfoDescription1, String brand) {
        this.handleId = handleId;
        this.fieldType = fieldType;
        this.name = name;
        this.description = description;
        this.productImageUrl = productImageUrl;
        this.collection = collection;
        this.sku = sku;
        this.ribbon = ribbon;
        this.price = price;
        this.surcharge = surcharge;
        this.visible = visible;
        this.discountMode = discountMode;
        this.discountValue = discountValue;
        this.inventory = inventory;
        this.weight = weight;
        this.cost = cost;
        this.additionalInfoTitle1 = additionalInfoTitle1;
        this.additionalInfoDescription1 = additionalInfoDescription1;
        this.brand = brand;
    }

    public String getHandleId() {
        return handleId;
    }

    public void setHandleId(String handleId) {
        this.handleId = handleId;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getRibbon() {
        return ribbon;
    }

    public void setRibbon(String ribbon) {
        this.ribbon = ribbon;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(String surcharge) {
        this.surcharge = surcharge;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getDiscountMode() {
        return discountMode;
    }

    public void setDiscountMode(String discountMode) {
        this.discountMode = discountMode;
    }

    public String getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(String discountValue) {
        this.discountValue = discountValue;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getProductOptionName1() {
        return productOptionName1;
    }

    public void setProductOptionName1(String productOptionName1) {
        this.productOptionName1 = productOptionName1;
    }

    public String getProductOptionType1() {
        return productOptionType1;
    }

    public void setProductOptionType1(String productOptionType1) {
        this.productOptionType1 = productOptionType1;
    }

    public String getProductOptionDescription1() {
        return productOptionDescription1;
    }

    public void setProductOptionDescription1(String productOptionDescription1) {
        this.productOptionDescription1 = productOptionDescription1;
    }

    public String getProductOptionName2() {
        return productOptionName2;
    }

    public void setProductOptionName2(String productOptionName2) {
        this.productOptionName2 = productOptionName2;
    }

    public String getProductOptionType2() {
        return productOptionType2;
    }

    public void setProductOptionType2(String productOptionType2) {
        this.productOptionType2 = productOptionType2;
    }

    public String getProductOptionDescription2() {
        return productOptionDescription2;
    }

    public void setProductOptionDescription2(String productOptionDescription2) {
        this.productOptionDescription2 = productOptionDescription2;
    }

    public String getProductOptionName3() {
        return productOptionName3;
    }

    public void setProductOptionName3(String productOptionName3) {
        this.productOptionName3 = productOptionName3;
    }

    public String getProductOptionType3() {
        return productOptionType3;
    }

    public void setProductOptionType3(String productOptionType3) {
        this.productOptionType3 = productOptionType3;
    }

    public String getProductOptionDescription3() {
        return productOptionDescription3;
    }

    public void setProductOptionDescription3(String productOptionDescription3) {
        this.productOptionDescription3 = productOptionDescription3;
    }

    public String getProductOptionName4() {
        return productOptionName4;
    }

    public void setProductOptionName4(String productOptionName4) {
        this.productOptionName4 = productOptionName4;
    }

    public String getProductOptionType4() {
        return productOptionType4;
    }

    public void setProductOptionType4(String productOptionType4) {
        this.productOptionType4 = productOptionType4;
    }

    public String getProductOptionDescription4() {
        return productOptionDescription4;
    }

    public void setProductOptionDescription4(String productOptionDescription4) {
        this.productOptionDescription4 = productOptionDescription4;
    }

    public String getProductOptionName5() {
        return productOptionName5;
    }

    public void setProductOptionName5(String productOptionName5) {
        this.productOptionName5 = productOptionName5;
    }

    public String getProductOptionType5() {
        return productOptionType5;
    }

    public void setProductOptionType5(String productOptionType5) {
        this.productOptionType5 = productOptionType5;
    }

    public String getProductOptionDescription5() {
        return productOptionDescription5;
    }

    public void setProductOptionDescription5(String productOptionDescription5) {
        this.productOptionDescription5 = productOptionDescription5;
    }

    public String getProductOptionName6() {
        return productOptionName6;
    }

    public void setProductOptionName6(String productOptionName6) {
        this.productOptionName6 = productOptionName6;
    }

    public String getProductOptionType6() {
        return productOptionType6;
    }

    public void setProductOptionType6(String productOptionType6) {
        this.productOptionType6 = productOptionType6;
    }

    public String getProductOptionDescription6() {
        return productOptionDescription6;
    }

    public void setProductOptionDescription6(String productOptionDescription6) {
        this.productOptionDescription6 = productOptionDescription6;
    }

    public String getAdditionalInfoTitle1() {
        return additionalInfoTitle1;
    }

    public void setAdditionalInfoTitle1(String additionalInfoTitle1) {
        this.additionalInfoTitle1 = additionalInfoTitle1;
    }

    public String getAdditionalInfoDescription1() {
        return additionalInfoDescription1;
    }

    public void setAdditionalInfoDescription1(String additionalInfoDescription1) {
        this.additionalInfoDescription1 = additionalInfoDescription1;
    }

    public String getAdditionalInfoTitle2() {
        return additionalInfoTitle2;
    }

    public void setAdditionalInfoTitle2(String additionalInfoTitle2) {
        this.additionalInfoTitle2 = additionalInfoTitle2;
    }

    public String getAdditionalInfoDescription2() {
        return additionalInfoDescription2;
    }

    public void setAdditionalInfoDescription2(String additionalInfoDescription2) {
        this.additionalInfoDescription2 = additionalInfoDescription2;
    }

    public String getAdditionalInfoTitle3() {
        return additionalInfoTitle3;
    }

    public void setAdditionalInfoTitle3(String additionalInfoTitle3) {
        this.additionalInfoTitle3 = additionalInfoTitle3;
    }

    public String getAdditionalInfoDescription3() {
        return additionalInfoDescription3;
    }

    public void setAdditionalInfoDescription3(String additionalInfoDescription3) {
        this.additionalInfoDescription3 = additionalInfoDescription3;
    }

    public String getAdditionalInfoTitle4() {
        return additionalInfoTitle4;
    }

    public void setAdditionalInfoTitle4(String additionalInfoTitle4) {
        this.additionalInfoTitle4 = additionalInfoTitle4;
    }

    public String getAdditionalInfoDescription4() {
        return additionalInfoDescription4;
    }

    public void setAdditionalInfoDescription4(String additionalInfoDescription4) {
        this.additionalInfoDescription4 = additionalInfoDescription4;
    }

    public String getAdditionalInfoTitle5() {
        return additionalInfoTitle5;
    }

    public void setAdditionalInfoTitle5(String additionalInfoTitle5) {
        this.additionalInfoTitle5 = additionalInfoTitle5;
    }

    public String getAdditionalInfoDescription5() {
        return additionalInfoDescription5;
    }

    public void setAdditionalInfoDescription5(String additionalInfoDescription5) {
        this.additionalInfoDescription5 = additionalInfoDescription5;
    }

    public String getAdditionalInfoTitle6() {
        return additionalInfoTitle6;
    }

    public void setAdditionalInfoTitle6(String additionalInfoTitle6) {
        this.additionalInfoTitle6 = additionalInfoTitle6;
    }

    public String getAdditionalInfoDescription6() {
        return additionalInfoDescription6;
    }

    public void setAdditionalInfoDescription6(String additionalInfoDescription6) {
        this.additionalInfoDescription6 = additionalInfoDescription6;
    }

    public String getCustomTextField1() {
        return customTextField1;
    }

    public void setCustomTextField1(String customTextField1) {
        this.customTextField1 = customTextField1;
    }

    public String getCustomTextCharLimit1() {
        return customTextCharLimit1;
    }

    public void setCustomTextCharLimit1(String customTextCharLimit1) {
        this.customTextCharLimit1 = customTextCharLimit1;
    }

    public String getCustomTextMandatory1() {
        return customTextMandatory1;
    }

    public void setCustomTextMandatory1(String customTextMandatory1) {
        this.customTextMandatory1 = customTextMandatory1;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}

