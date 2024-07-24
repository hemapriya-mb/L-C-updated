package org.itt.entity;

import java.io.Serial;
import java.io.Serializable;

public class Item implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int itemId;
    private String itemName;
    private double price;
    private String availabilityStatus;
    private String mealType;
    private String description;
    private double averageRating;
    private String foodType;
    private String spiceLevel;
    private String cuisineType;
    private Boolean sweet;

    public Item() {
    }

    public Item(int itemId, String itemName, double price, String availabilityStatus, String mealType,
                String description, String foodType, String spiceLevel, String cuisineType, Boolean sweet) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.availabilityStatus = availabilityStatus;
        this.mealType = mealType;
        this.description = description;
        this.foodType = foodType;
        this.spiceLevel = spiceLevel;
        this.cuisineType = cuisineType;
        this.sweet = sweet;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getSpiceLevel() {
        return spiceLevel;
    }

    public void setSpiceLevel(String spiceLevel) {
        this.spiceLevel = spiceLevel;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public Boolean getSweet() {
        return sweet;
    }

    public void setSweet(Boolean sweet) {
        this.sweet = sweet;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", availabilityStatus='" + availabilityStatus + '\'' +
                ", mealType='" + mealType + '\'' +
                ", description='" + description + '\'' +
                ", averageRating=" + averageRating +
                ", foodType='" + foodType + '\'' +
                ", spiceLevel='" + spiceLevel + '\'' +
                ", cuisineType='" + cuisineType + '\'' +
                ", sweet=" + sweet +
                '}';
    }
}
