package com.example.selforderingkiosk;

public class Beverages {
    private String nameBeverages;
    String priceBeverages;
    private int imgBeverages;

    public Beverages(String nameBeverages, String priceBeverages, int imgBeverages) {
        this.nameBeverages = nameBeverages;
        this.priceBeverages = priceBeverages;
        this.imgBeverages = imgBeverages;
    }

    public String getNameBeverages() {
        return nameBeverages;
    }


    public String getPriceBeverages() {
        return priceBeverages;
    }


    public int getImgBeverages() {
        return imgBeverages;
    }
}