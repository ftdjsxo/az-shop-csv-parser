package com.example.azshopcsvparser.model;

public class SetUrlRequest {

    private String value;
    private String ao;


    public SetUrlRequest(String value, String ao) {
        this.value = value;
        this.ao = ao;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAo() {
        return ao;
    }

    public void setAo(String ao) {
        this.ao = ao;
    }
}
