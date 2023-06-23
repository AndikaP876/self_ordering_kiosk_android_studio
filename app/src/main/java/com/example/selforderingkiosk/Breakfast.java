package com.example.selforderingkiosk;

public class Breakfast {
    private String nameBreakfast;
    String priceBreakfast;
    private int imgBreakfast;

    public Breakfast(String nameBreakfast, String priceBreakfast, int imgBreakfast) {
        this.nameBreakfast = nameBreakfast;
        this.priceBreakfast = priceBreakfast;
        this.imgBreakfast = imgBreakfast;
    }

    public String getNameBreakfast() {
        return nameBreakfast;
    }


    public String getPriceBreakfast() {
        return priceBreakfast;
    }


    public int getImgBreakfast() {
        return imgBreakfast;
    }
}
