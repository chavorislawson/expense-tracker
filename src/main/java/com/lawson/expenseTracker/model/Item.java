package com.lawson.expenseTracker.model;

import com.lawson.expenseTracker.enums.ItemCategory;

import java.util.Date;

public class Item {

    private String name;
    private String description;
    private ItemCategory category;
    private double price;
    private Date purchaseDate;
    private Date purchaseTime;

    /**
     * Where this purchase took place. Ex. Chic-Fil-A
     */
    private String purchasePlace;

    /**
     * Where this purchase took place. Ex. Atlanta, GAs
     */
    private String purchaseLocation;

    //getMostFrequentItemsByPurchasePlace

    public Item(){}

    public Item(String name, String description, ItemCategory category, double price, Date purchaseDate, Date purchaseTime, String purchasePlace, String purchaseLocation) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.purchaseDate = purchaseDate;
        this.purchaseTime = purchaseTime;
        this.purchasePlace = purchasePlace;
        this.purchaseLocation = purchaseLocation;
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

    public ItemCategory getCategory() {
        return category;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getPurchasePlace() {
        return purchasePlace;
    }

    public void setPurchasePlace(String purchasePlace) {
        this.purchasePlace = purchasePlace;
    }

    public String getPurchaseLocation() {
        return purchaseLocation;
    }

    public void setPurchaseLocation(String purchaseLocation) {
        this.purchaseLocation = purchaseLocation;
    }
}
