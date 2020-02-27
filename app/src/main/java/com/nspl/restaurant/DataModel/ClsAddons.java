package com.nspl.restaurant.DataModel;

public class ClsAddons {
    String addons = "";
    Double price = 0.0;

    public ClsAddons(String addons, Double price) {
        this.addons = addons;
        this.price = price;
    }

    public String getAddons() {
        return addons;
    }

    public void setAddons(String addons) {
        this.addons = addons;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
