package com.app.nikhil.iconfinderapp.Pojo;


import com.google.gson.annotations.SerializedName;

public class IconPrice {

    String price;

    License license;

    String currency;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
